package com.bank.ServiceTransfer.service.impl;

import com.bank.ServiceTransfer.dao.*;
import com.bank.ServiceTransfer.pojo.BankTransferLog;
import com.bank.ServiceTransfer.pojo.BankTransferLogExample;
import com.bank.ServiceTransfer.service.TransferService;
import com.bank.ServiceTransfer.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private BankTransferLogMapper bankTransferLogMapper;

    @Override
    @HystrixCommand(fallbackMethod = "errorMethodInTransfer",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public BankResult getTransferLogs() {
        BankTransferLogExample bankTransferLogExample = new BankTransferLogExample();
        BankTransferLogExample.Criteria criteria = bankTransferLogExample.createCriteria();
        criteria.andTransferIdIsNotNull();
        List<BankTransferLog> bankTransferLogList = bankTransferLogMapper.selectByExample(bankTransferLogExample);

        return BankResult.ok(bankTransferLogList);
    }

    @Override
    @HystrixCommand(fallbackMethod = "errorMethodInTransfer",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public BankResult getOneTransferLog(String transferId) {
        BankTransferLog bankTransferLog = bankTransferLogMapper.selectByPrimaryKey(transferId);
        return BankResult.ok(bankTransferLog);
    }
    public BankResult errorMethodInTransfer(){
        return BankResult.build(400, "Hystrix in Service-Transfer-Other.", "");
    }
}
