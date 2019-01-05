package com.bank.depositaddservice.service;

import com.bank.depositaddservice.pojo.BankDeposit;
import com.bank.depositaddservice.utils.BankResult;

public interface DepositAddService {
    BankResult insert(BankDeposit bankDeposit, String password);
}
