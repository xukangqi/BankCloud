package com.bank.depositaccountservice.service;

import com.bank.depositaccountservice.utils.BankResult;

public interface DepositAccountService {
    BankResult getBankDepositFromAccount(String account);
}
