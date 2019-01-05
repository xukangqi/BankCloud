package com.bank.depositrateservice.service;

import com.bank.depositrateservice.pojo.BankDepositRate;
import com.bank.depositrateservice.utils.BankResult;

public interface DepositRateService {
    BankResult insert(BankDepositRate bankDepositRate);

    BankResult update(BankDepositRate bankDepositRate);
}
