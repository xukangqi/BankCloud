package com.bank.ServiceTransfer.service.impl;

import com.bank.ServiceTransfer.dao.*;
import com.bank.ServiceTransfer.pojo.BankAccount;
import com.bank.ServiceTransfer.pojo.BankCustomer;
import com.bank.ServiceTransfer.pojo.BankTransferLog;
import com.bank.ServiceTransfer.pojo.BankTransferLogExample;
import com.bank.ServiceTransfer.service.TransferService;
import com.bank.ServiceTransfer.utils.BankResult;
import com.bank.ServiceTransfer.utils.MD5;
import com.bank.ServiceTransfer.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private BankTransferLogMapper bankTransferLogMapper;
    @Autowired
    private BankAccountMapper bankAccountMapper;
    @Autowired
    private BankCustomerMapper bankCustomerMapper;

    private long datacenterId = 3L;  //数据中心
    private long machineId ;     //机器标识

    @Override
    @HystrixCommand(fallbackMethod = "errorMethodInTransfer",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public BankResult createTransfer(String name, String phone, String transferOutAccount, String transferInAccount, String password, double amount) {
        machineId = 1L;

        BankAccount outAccount = bankAccountMapper.selectByPrimaryKey(transferOutAccount);
        BankCustomer outAccountCustomer = bankCustomerMapper.selectByPrimaryKey(outAccount.getCustId());

        String pw = MD5.string2MD5(password);

        BankAccount inAccount = bankAccountMapper.selectByPrimaryKey(transferInAccount);
        if (!outAccountCustomer.getCustName().equals(name))
            return BankResult.build(400, "用户姓名不匹配！", "");

        if (!outAccountCustomer.getPhone().equals(phone))
            return BankResult.build(400, "用户电话不匹配！", "");

        if (outAccount == null)
            return BankResult.build(400, "转账账户不存在！", "");

        if (!outAccount.getPassword().equals(pw)) return BankResult.build(400, "密码错误！", "");

        if (inAccount == null)
            return BankResult.build(400, "收款账户不存在！", "");

        if (outAccount.getBalances() < amount)
            return BankResult.build(400, "转账账户余额不足！", "");

        SnowFlake snowFlake = new SnowFlake(datacenterId, machineId);
        long transferId = snowFlake.nextId();

        BankTransferLog bankTransferLog = new BankTransferLog();

        bankTransferLog.setTransferId(String.valueOf(transferId));
        bankTransferLog.setTransferOutAccount(transferOutAccount);
        bankTransferLog.setTransferInAccount(transferInAccount);
        bankTransferLog.setAmount(amount);

        outAccount.setBalances(outAccount.getBalances() - amount);
        bankAccountMapper.updateByPrimaryKey(outAccount);
        bankTransferLog.setTransferDate(String.valueOf(System.currentTimeMillis()));

        inAccount.setBalances(inAccount.getBalances() + amount);
        bankAccountMapper.updateByPrimaryKey(inAccount);
        bankTransferLog.setReceiveDate(String.valueOf(System.currentTimeMillis()));

        bankTransferLogMapper.insert(bankTransferLog);

        return BankResult.ok(bankTransferLog.getTransferId());
    }

    @Override
    public BankResult getTransferLogs() {
        BankTransferLogExample bankTransferLogExample = new BankTransferLogExample();
        BankTransferLogExample.Criteria criteria = bankTransferLogExample.createCriteria();
        criteria.andTransferIdIsNotNull();
        List<BankTransferLog> bankTransferLogList = bankTransferLogMapper.selectByExample(bankTransferLogExample);

        return BankResult.ok(bankTransferLogList);
    }

    @Override
    public BankResult getOneTransferLog(String transferId) {
        BankTransferLog bankTransferLog = bankTransferLogMapper.selectByPrimaryKey(transferId);
        return BankResult.ok(bankTransferLog);
    }
    public BankResult errorMethodInTransfer(){
        return BankResult.build(400, "Hystrix in ServiceTransfer.", "");
    }
}
