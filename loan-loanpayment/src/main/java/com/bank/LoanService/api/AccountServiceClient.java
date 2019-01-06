package com.bank.LoanService.api;

import com.bank.LoanService.pojo.BankAccount;
import com.bank.LoanService.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "customer-service")
public interface AccountServiceClient {
    // 更新账户信息
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/editAccountSel",
            consumes = "application/json"
    )
    public BankResult updateAccountSel(@RequestBody BankAccount account);
}
