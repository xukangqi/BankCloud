package com.bank.LoanService.service;

import com.bank.LoanService.pojo.BankLoanPayment;
import com.bank.LoanService.pojo.BankLoanPaymentExample;
import com.bank.LoanService.utils.BankResult;

public interface LoanService {
    public BankResult insertData(BankLoanPayment bankLoan);
    public BankResult updateByPrimaryKeySelective(BankLoanPayment bankLoan);
    public BankResult selectByExample(BankLoanPaymentExample bankLoanPaymentExample);
    public BankResult getPaymentOneInfo(String value);
}
