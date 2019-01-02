package com.bank.ServiceRemitHotspot.service.impl;

import com.bank.ServiceRemitHotspot.dao.BankAccountMapper;
import com.bank.ServiceRemitHotspot.dao.BankRemitLogMapper;
import com.bank.ServiceRemitHotspot.pojo.BankAccount;
import com.bank.ServiceRemitHotspot.pojo.BankRemitLog;
import com.bank.ServiceRemitHotspot.pojo.BankRemitLogExample;
import com.bank.ServiceRemitHotspot.service.RemitService;
import com.bank.ServiceRemitHotspot.utils.BankResult;
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
    private BankAccountMapper bankAccountMapper;

    @Override
    @HystrixCommand(fallbackMethod = "errorMethodInRemit",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public BankResult getRemit(String remitInAccount, String remitId) {
        BankAccount inAccount = bankAccountMapper.selectByPrimaryKey(remitInAccount);
        BankRemitLog bankRemitLog = bankRemitLogMapper.selectByPrimaryKey(remitId);
        if (bankRemitLog == null) return BankResult.build(400, "汇票不存在！", "");

        if (!bankRemitLog.getRemitInAccount().equals(remitInAccount))
            return BankResult.build(400, "提款账户不匹配！", "");

        if (!bankRemitLog.getRemitArriveDate().equals("Unpaid"))
            return BankResult.build(400, "汇款已被提取！", "");

        bankRemitLog.setRemitArriveDate(String.valueOf(System.currentTimeMillis()));
        bankRemitLogMapper.updateByPrimaryKey(bankRemitLog);

        inAccount.setBalances(inAccount.getBalances() + bankRemitLog.getAmount());
        bankAccountMapper.updateByPrimaryKey(inAccount);

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
