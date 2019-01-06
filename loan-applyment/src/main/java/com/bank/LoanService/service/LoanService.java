package com.bank.LoanService.service;

import com.bank.LoanService.pojo.loan.BankLoanApplyInfo;
import com.bank.LoanService.utils.BankResult;

public interface LoanService {
    BankResult dealApplyment(BankLoanApplyInfo bankLoanApplyInfo);
    BankResult getInterestRate();
    BankResult getInterestOneRate(int value);
}
