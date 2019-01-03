package com.bank.ServiceRemitHotspot.service;

import com.bank.ServiceRemitHotspot.utils.BankResult;

public interface RemitService {
    BankResult getRemit(String remitInAccount, String remitId);
    BankResult getRemitLogs();
    BankResult getOneRemitLog(String remitId);
}
