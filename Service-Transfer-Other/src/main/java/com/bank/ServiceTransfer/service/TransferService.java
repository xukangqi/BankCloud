package com.bank.ServiceTransfer.service;

import com.bank.ServiceTransfer.utils.BankResult;

public interface TransferService {
    BankResult getTransferLogs();
    BankResult getOneTransferLog(String transferId);
}
