package com.bank.ServiceTransfer.api;

import com.bank.ServiceTransfer.pojo.BankAccount;
import com.bank.ServiceTransfer.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "customer-service")
public interface FeignAccountCustomer {
    // 返回该id的账户信息
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/account/accountId/{accountId}",
            consumes = "application/json"
    )
    public BankResult getAccount(@PathVariable("accountId") String accountId);

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/editAccount",
            consumes = "application/json"
    )
    @ResponseBody
    public BankResult updateAccount(@RequestBody BankAccount account);
}
