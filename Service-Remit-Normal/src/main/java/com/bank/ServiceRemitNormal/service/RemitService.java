package com.bank.ServiceRemitNormal.service;

import com.bank.ServiceRemitNormal.utils.BankResult;

public interface RemitService {
    BankResult createRemit(String name, String phone, String remitOutAccount, String remitInAccount, String password, double amount);
}
