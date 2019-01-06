package com.bank.LoanService.service;

import com.bank.LoanService.pojo.loan.BankLoanPaymentInfo;
import com.bank.LoanService.utils.BankResult;

public interface LoanService {
    BankResult payForLoan(BankLoanPaymentInfo bankLoanPaymentInfo);
    BankResult getPaylog(String value);

}
