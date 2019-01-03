package com.bank.ServiceRemitHotspot.service.impl;

import com.bank.ServiceRemitHotspot.api.FeignAccountCustomer;
import com.bank.ServiceRemitHotspot.dao.BankRemitLogMapper;
import com.bank.ServiceRemitHotspot.pojo.BankAccount;
import com.bank.ServiceRemitHotspot.pojo.BankRemitLog;
import com.bank.ServiceRemitHotspot.pojo.BankRemitLogExample;
import com.bank.ServiceRemitHotspot.service.RemitService;
import com.bank.ServiceRemitHotspot.utils.BankResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.List;

@Service
public class RemitServiceImpl implements RemitService {
    @Autowired
    private BankRemitLogMapper bankRemitLogMapper;
    @Autowired
    private FeignAccountCustomer feignAccountCustomer;

    //已测试 2019年1月3日 11:32:37
    @Override
    @HystrixCommand(fallbackMethod = "errorMethodInRemit",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public BankResult getRemit(String remitInAccount, String remitId) {
        ObjectMapper mapper = new ObjectMapper();
        BankAccount inAccount = mapper.convertValue(feignAccountCustomer.getAccount(remitInAccount).getData(), BankAccount.class);
        //BankAccount inAccount = bankAccountMapper.selectByPrimaryKey(remitInAccount);
        BankRemitLog bankRemitLog = bankRemitLogMapper.selectByPrimaryKey(remitId);
        if (bankRemitLog == null) return BankResult.build(400, "汇票不存在！", "");

        if (!bankRemitLog.getRemitInAccount().equals(remitInAccount))
            return BankResult.build(400, "提款账户不匹配！", "");

        if (!bankRemitLog.getRemitArriveDate().equals("Unpaid"))
            return BankResult.build(400, "汇款已被提取！", "");

        bankRemitLog.setRemitArriveDate(String.valueOf(System.currentTimeMillis()));
        bankRemitLogMapper.updateByPrimaryKey(bankRemitLog);

        inAccount.setBalances(inAccount.getBalances() + bankRemitLog.getAmount());
        feignAccountCustomer.updateAccount(inAccount);
        //bankAccountMapper.updateByPrimaryKey(inAccount);

        return BankResult.ok();
    }

    @Override
    @HystrixCommand(fallbackMethod = "errorMethodInRemit",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public BankResult getRemitLogs() {
        BankRemitLogExample bankRemitLogExample = new BankRemitLogExample();
        BankRemitLogExample.Criteria criteria = bankRemitLogExample.createCriteria();
        criteria.andRemitIdIsNotNull();
        List<BankRemitLog> bankRemitLogsList = bankRemitLogMapper.selectByExample(bankRemitLogExample);

        return BankResult.ok(bankRemitLogsList);
    }

    @Override
    @HystrixCommand(fallbackMethod = "errorMethodInRemit",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public BankResult getOneRemitLog(String remitId) {
        BankRemitLog bankRemitLog = bankRemitLogMapper.selectByPrimaryKey(remitId);
        return BankResult.ok(bankRemitLog);
    }
    public BankResult errorMethodInRemit(){
        return BankResult.build(400, "Hystrix in ServiceRemitHotspot.", "");
    }
}
