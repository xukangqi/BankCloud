package com.bank.ServiceRemitNormal.service.impl;

import com.bank.ServiceRemitNormal.api.RemitProducerClient;
import com.bank.ServiceRemitNormal.dao.*;
import com.bank.ServiceRemitNormal.pojo.BankAccount;
import com.bank.ServiceRemitNormal.pojo.BankCustomer;
import com.bank.ServiceRemitNormal.pojo.BankRemitLog;
import com.bank.ServiceRemitNormal.pojo.BankRemitLogExample;
import com.bank.ServiceRemitNormal.service.RemitService;
import com.bank.ServiceRemitNormal.utils.BankResult;
import com.bank.ServiceRemitNormal.utils.MD5;
import com.bank.ServiceRemitNormal.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemitServiceImpl implements RemitService {
    @Autowired
    private BankRemitLogMapper bankRemitLogMapper;
    @Autowired
    private BankAccountMapper bankAccountMapper;
    @Autowired
    private BankCustomerMapper bankCustomerMapper;
    @Autowired
    private RemitProducerClient remitProducerClient;

    private long datacenterId = 4L;  //数据中心
    private long machineId ;     //机器标识

    @Override
    public BankResult createRemit(String name, String phone, String remitOutAccount, String remitInAccount, String password, double amount) {
        machineId = 1L;

        BankAccount outAccount = bankAccountMapper.selectByPrimaryKey(remitOutAccount);
        BankCustomer outAccountCustomer = bankCustomerMapper.selectByPrimaryKey(outAccount.getCustId());
        BankAccount inAccount = bankAccountMapper.selectByPrimaryKey(remitInAccount);

        String pw = MD5.string2MD5(password);

        if (!outAccountCustomer.getCustName().equals(name))
            return BankResult.build(400, "用户姓名不匹配！", "");

        if (!outAccountCustomer.getPhone().equals(phone))
            return BankResult.build(400, "用户电话不匹配！", "");

        if (outAccount == null)
            return BankResult.build(400, "汇款账户不存在！", "");

        if (!outAccount.getPassword().equals(pw))
            return BankResult.build(400, "密码错误！", "");

        if (inAccount == null)
            return BankResult.build(400, "提款账户不存在！", "");

        if (outAccount.getBalances() < amount)
            return BankResult.build(400, "汇款账户余额不足！", "");

        SnowFlake snowFlake = new SnowFlake(datacenterId, machineId);
        long remitId = snowFlake.nextId();

        BankRemitLog bankRemitLog = new BankRemitLog();

        bankRemitLog.setRemitId(String.valueOf(remitId));
        bankRemitLog.setRemitOutAccount(remitOutAccount);
        bankRemitLog.setRemitInAccount(remitInAccount);
        bankRemitLog.setAmount(amount);

        outAccount.setBalances(outAccount.getBalances() - amount);
        bankAccountMapper.updateByPrimaryKey(outAccount);
        bankRemitLog.setRemitGenerateDate(String.valueOf(System.currentTimeMillis()));
        bankRemitLog.setRemitArriveDate(String.valueOf("Unpaid"));

        bankRemitLogMapper.insert(bankRemitLog);

        return BankResult.ok(bankRemitLog.getRemitId());
    }

    @Override
    public BankResult getRemit(String remitInAccount, String remitId) {
        return remitProducerClient.getRemit(remitInAccount, remitId);
    }

    @Override
    public BankResult getRemitLogs() {
        return remitProducerClient.getRemitLogs();
    }

    @Override
    public BankResult getOneRemitLog(String remitId) {
        return remitProducerClient.getOneRemitLog(remitId);
    }

}
