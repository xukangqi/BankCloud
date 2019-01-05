package com.bank.depositaddservice.service.impl;

import com.bank.depositaddservice.api.AccountClient;
import com.bank.depositaddservice.dao.BankDepositMapper;
import com.bank.depositaddservice.pojo.BankAccount;
import com.bank.depositaddservice.pojo.BankDeposit;
import com.bank.depositaddservice.service.DepositAddService;
import com.bank.depositaddservice.utils.BankResult;
import com.bank.depositaddservice.utils.SnowFlake;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositAddServiceImpl implements DepositAddService {

    private static SnowFlake snowFlake;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private BankDepositMapper bankDepositMapper;

    @Override
    @HystrixCommand(fallbackMethod = "errorMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            }
    )
    public BankResult insert(BankDeposit bankDeposit, String password) {
        if(bankDeposit == null || password == null){
            return BankResult.build(400, "参数错误");
        }

        if(snowFlake == null){
            snowFlake = new SnowFlake(1, 1);
        }

        if(bankDeposit.getAccount() == null)
            return BankResult.build(400, "参数错误");

        BankResult passwordResult = accountClient.verify(bankDeposit.getAccount(), password);
        if(!(new Integer(200).equals(passwordResult.getStatus()))){
            return BankResult.build(400, "账号密码错误");
        }

        ObjectMapper mapper = new ObjectMapper();
        BankAccount bankAccount = mapper.convertValue(passwordResult.getData(), BankAccount.class);

        bankDeposit.setDepositDate(Long.toString(System.currentTimeMillis()));
        bankDeposit.setCustId(bankAccount.getCustId());
        bankDeposit.setDepositId(Long.toString(snowFlake.nextId()));
        bankDeposit.setDepositFlag("0");
        bankDepositMapper.insert(bankDeposit);

        double balances = bankAccount.getBalances();
        double blockedBalances = bankAccount.getBlockedBalances();
        if(!bankDeposit.getDepositType().equals("活期存款"))
            bankAccount.setBlockedBalances(blockedBalances+bankDeposit.getDepositMoney());
        bankAccount.setBalances(balances + bankDeposit.getDepositMoney());
        accountClient.updateAccount(bankAccount);

        return BankResult.build(200, "存款成功");


    }

    //如果调用超时调用备用方法
    public String errorMethod(){
        return  "error";
    }
}
