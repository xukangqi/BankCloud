package com.bank.LoanService.api;

import com.bank.LoanService.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "customer-service")
public interface AccountServiceClient {
    // 返回该id的账户信息
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/account/accountId/{accountId}",
            consumes = "application/json"
    )
    public BankResult getAccount(@PathVariable("accountId") String accountId);
}
