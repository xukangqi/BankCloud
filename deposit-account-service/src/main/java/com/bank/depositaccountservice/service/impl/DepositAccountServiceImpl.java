package com.bank.depositaccountservice.service.impl;

import com.bank.depositaccountservice.dao.BankDepositMapper;
import com.bank.depositaccountservice.pojo.BankDeposit;
import com.bank.depositaccountservice.pojo.BankDepositExample;
import com.bank.depositaccountservice.service.DepositAccountService;
import com.bank.depositaccountservice.utils.BankResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositAccountServiceImpl implements DepositAccountService {

    @Autowired
    private BankDepositMapper bankDepositMapper;

    @Override
    @HystrixCommand(fallbackMethod = "errorMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            }
    )
    public BankResult getBankDepositFromAccount(String account) {
        if(account == null || account.equals("")){
            return BankResult.build(400, "参数错误");
        }
        BankDepositExample bankDepositExample = new BankDepositExample();
        bankDepositExample.createCriteria().andAccountEqualTo(account);
        bankDepositExample.setOrderByClause("deposit_id desc");
        List<BankDeposit> bankDeposits = bankDepositMapper.selectByExample(bankDepositExample);
        return new BankResult(200,"查询成功",bankDeposits);
    }

    //如果调用超时调用备用方法
    public String errorMethod(){
        return "error";
    }
}
