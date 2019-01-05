package com.bank.depositrateservice.service.impl;

import com.bank.depositrateservice.mapper.BankDepositRateMapper;
import com.bank.depositrateservice.pojo.BankDepositRate;
import com.bank.depositrateservice.service.DepositRateService;
import com.bank.depositrateservice.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositRateServiceImpl implements DepositRateService {

    @Autowired
    private BankDepositRateMapper bankDepositRateMapper;

    @Override
    public BankResult insert(BankDepositRate bankDepositRate) {
        if (bankDepositRate == null) {
            return BankResult.build(400, "参数错误");
        }
        bankDepositRate.setUpdateDate(Long.toString(System.currentTimeMillis()));
        bankDepositRateMapper.insert(bankDepositRate);
        return BankResult.build(200, "新增成功");
    }

    @Override
    public BankResult update(BankDepositRate bankDepositRate) {
        if (bankDepositRate == null) {
            return BankResult.build(400, "参数错误");
        }
        bankDepositRateMapper.updateByPrimaryKeySelective(bankDepositRate);
        return BankResult.build(200, "修改成功");
    }
}
