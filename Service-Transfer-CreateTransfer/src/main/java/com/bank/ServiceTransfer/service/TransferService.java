package com.bank.ServiceTransfer.service;

import com.bank.ServiceTransfer.utils.BankResult;

public interface TransferService {
    BankResult createTransfer(String name, String phone, String transferOutAccount, String transferInAccount, String password, double amount);
}
