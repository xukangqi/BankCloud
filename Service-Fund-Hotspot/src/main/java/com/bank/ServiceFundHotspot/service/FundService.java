package com.bank.ServiceFundHotspot.service;

import com.bank.ServiceFundHotspot.utils.BankResult;

public interface FundService {
    BankResult getFundProducts();
    BankResult getOneFundProduct(String fundId);
}
