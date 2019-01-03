package com.bank.LoanService.service;

import com.bank.LoanService.pojo.BankLoan;
import com.bank.LoanService.utils.BankResult;

public interface LoanService {
    public BankResult insertData(BankLoan bankLoan);
    public BankResult updateByPrimaryKeySelective(BankLoan bankLoan);
    public BankResult sentAllRecords();
    public BankResult sentOneRecord(String transId) ;
}
