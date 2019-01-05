package com.bank.LoanService.service;

import com.bank.LoanService.pojo.BankLoanPayment;
import com.bank.LoanService.pojo.BankLoanPaymentExample;
import com.bank.LoanService.utils.BankResult;

public interface LoanService {
    public BankResult insertData(BankLoanPayment bankLoan);
    public BankResult payforLoan(String transId,String amounts,String account,Double amountInAcc);
    public BankResult getPaymentOneInfo(String value);
}
