package com.bank.feigncustomerconsumer.service.hystrix;

import com.bank.feigncustomerconsumer.pojo.BankAccount;
import com.bank.feigncustomerconsumer.service.ICustomerAddAccountService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddAccountHystrix implements ICustomerAddAccountService {
    @Override
    public BankResult addAccount(String custName, BankAccount account) {
        return null;
    }
}
