package com.bank.withdrawaddservice.service.impl;

import com.bank.withdrawaddservice.api.AccountClient;
import com.bank.withdrawaddservice.dao.BankWithdrawMapper;
import com.bank.withdrawaddservice.pojo.BankAccount;
import com.bank.withdrawaddservice.pojo.BankWithdraw;
import com.bank.withdrawaddservice.service.WithdrawAddService;
import com.bank.withdrawaddservice.utils.BankResult;
import com.bank.withdrawaddservice.utils.SnowFlake;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawAddServiceImpl implements WithdrawAddService {

    private static SnowFlake snowFlake;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private BankWithdrawMapper bankWithdrawMapper;

    @Override
    @HystrixCommand(fallbackMethod = "errorMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            }
    )
    public BankResult insert(BankWithdraw bankWithdraw, String password) {
        if(bankWithdraw == null || password == null){
            return BankResult.build(400, "参数错误");
        }

        if(snowFlake == null){
            snowFlake = new SnowFlake(1, 1);
        }

        if(bankWithdraw.getAccount() == null)
            return BankResult.build(400, "参数错误");

        BankResult passwordResult = accountClient.verify(bankWithdraw.getAccount(), password);
        if(!(new Integer(200).equals(passwordResult.getStatus()))){
            return BankResult.build(400, "账号密码错误");
        }

        ObjectMapper mapper = new ObjectMapper();
        BankAccount bankAccount = mapper.convertValue(passwordResult.getData(), BankAccount.class);

        double balances = bankAccount.getBalances();
        double blockedBalances = bankAccount.getBlockedBalances();
        if (balances - blockedBalances < bankWithdraw.getWithdrawMoney())
            return BankResult.build(400, "余额不足");
        balances -= bankWithdraw.getWithdrawMoney();
        bankAccount.setBalances(balances);
        accountClient.updateAccount(bankAccount);

        bankWithdraw.setCustId(bankAccount.getCustId());
        bankWithdraw.setWithdrawDate(Long.toString(System.currentTimeMillis()));
        bankWithdraw.setArriveTime(Long.toString(System.currentTimeMillis() + 2000));
        bankWithdraw.setWithdrawId(Long.toString(snowFlake.nextId()));
        bankWithdrawMapper.insert(bankWithdraw);
        return BankResult.build(200, "取款成功");
    }

    //如果调用超时调用备用方法
    public String errorMethod(){
        return  "error";
    }
}
