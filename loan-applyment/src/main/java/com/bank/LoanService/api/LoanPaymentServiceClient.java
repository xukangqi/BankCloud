package com.bank.LoanService.api;

import com.bank.LoanService.pojo.BankLoanPayment;
import com.bank.LoanService.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "loan-loanpayment")
public interface LoanPaymentServiceClient {
    // 插入贷款还款信息
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/loan/insert",
            consumes = "application/json"
    )
    public BankResult insertLoanPayment(BankLoanPayment bankLoan);
}
