package com.bank.depositrategetservice.service.impl;

import com.bank.depositrategetservice.mapper.BankDepositRateMapper;
import com.bank.depositrategetservice.pojo.BankDepositRate;
import com.bank.depositrategetservice.pojo.BankDepositRateExample;
import com.bank.depositrategetservice.service.DepositRateGetService;
import com.bank.depositrategetservice.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositRateGetServiceImpl implements DepositRateGetService {

    @Autowired
    private BankDepositRateMapper bankDepositRateMapper;

    @Override
    public BankResult getAllRate() {
        BankDepositRateExample bankDepositRateExample = new BankDepositRateExample();
        bankDepositRateExample.setOrderByClause("update_date desc");
        List<BankDepositRate> bankDepositRates = bankDepositRateMapper.selectByExample(bankDepositRateExample);
        if (bankDepositRates == null || bankDepositRates.size() == 0) {
            return BankResult.build(400, "查询失败");
        }
        return BankResult.build(200, "查询成功", bankDepositRates.get(0));
    }
}
