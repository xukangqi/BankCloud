package com.bank.LoanService.api;

import com.bank.LoanService.pojo.BankLoan;
import com.bank.LoanService.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "loan-loandata")
public interface LoanDataServiceClient {
    // 插入贷款信息
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/loan/insert",
            consumes = "application/json"
    )
    public BankResult insertLoan(BankLoan bankLoan);
}
