package com.bank.LoanService.api;

import com.bank.LoanService.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "loan-loanpayment")
public interface LoanPaymentServiceClient {
    // 插入贷款还款信息
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/loan/payforLoan/{transId}/{amount}/{account}/{amountInAccount}",
            consumes = "application/json"
    )
    public BankResult payforLoan(@PathVariable("transId")String transId,@PathVariable("amount")String amount,
                                 @PathVariable("account")String account,@PathVariable("amountInAccount")Double amountInAccount);
}
