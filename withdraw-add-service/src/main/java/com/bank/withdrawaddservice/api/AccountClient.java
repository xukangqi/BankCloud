package com.bank.withdrawaddservice.api;

import com.bank.withdrawaddservice.pojo.BankAccount;
import com.bank.withdrawaddservice.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("customer-service")
public interface AccountClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/account/verify",
            consumes = "application/json"
    )
    BankResult verify(@RequestParam(value = "accountId") String accountId, @RequestParam(value = "password") String password);

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/editAccount",
            consumes = "application/json"
    )
    BankResult updateAccount(@RequestBody BankAccount account);
}
