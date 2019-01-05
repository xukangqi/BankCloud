package com.bank.LoanService.api;

import com.bank.LoanService.pojo.BankLoan;
import com.bank.LoanService.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "loan-loandata")
public interface LoanDataServiceClient {
    // 返回该id的贷款信息
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/loan/{id}",
            consumes = "application/json"
    )
    public BankResult getOneRecord(@PathVariable("id") String id);
    //更新贷款信息
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/loan/updateByEntity",
            consumes = "application/json"
    )
    public BankResult UpdateLoan(BankLoan bankLoan);
}
