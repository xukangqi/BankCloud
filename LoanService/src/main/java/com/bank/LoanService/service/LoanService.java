package com.bank.LoanService.service;

import com.bank.LoanService.pojo.loan.BankLoanApplyInfo;
import com.bank.LoanService.pojo.loan.BankLoanPaymentInfo;
import com.bank.LoanService.utils.BankResult;

public interface LoanService {
    BankResult dealApplyment(BankLoanApplyInfo bankLoanApplyInfo);
    BankResult sentAllRecords();
    BankResult payForLoan(BankLoanPaymentInfo bankLoanPaymentInfo);
    BankResult sentOneRecord(String transId);
    BankResult getInterestRate();
    BankResult getInterestOneRate(int value);
    BankResult getPaylog(String value);
    BankResult getPaymentOneInfo(String value);
}
