package com.bank.withdrawaddservice.service;

import com.bank.withdrawaddservice.pojo.BankWithdraw;
import com.bank.withdrawaddservice.utils.BankResult;

public interface WithdrawAddService {
    BankResult insert(BankWithdraw bankWithdraw, String password);
}
