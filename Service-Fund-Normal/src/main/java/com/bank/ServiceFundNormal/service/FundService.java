package com.bank.ServiceFundNormal.service;

import com.bank.ServiceFundNormal.utils.BankResult;

public interface FundService {
    BankResult createFundProduct(String type, double purchaseRate, double netAssetValue, double redemptionRate);

    BankResult createFundPurchaseTx(String name, String phone, String account, String fundId, double amount, String password, String reviewerId);

    BankResult createFundRedemptionTx(String account, String fundId, double share, String password, String reviewerId);

    BankResult getFundLogs();

    BankResult getOneFundLog(String fundTxId);

    BankResult getFundHolds();

    BankResult getOneFundHold(String account, String fundId);
}
