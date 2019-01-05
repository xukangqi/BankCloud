package com.bank.withdrawaccountservice.service;

import com.bank.withdrawaccountservice.utils.BankResult;

public interface WithdrawAccountService {
    BankResult getBankWithdrawByAccount(String account);
}
