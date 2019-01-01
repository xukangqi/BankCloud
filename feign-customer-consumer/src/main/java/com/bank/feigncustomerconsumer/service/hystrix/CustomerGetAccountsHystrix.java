package com.bank.feigncustomerconsumer.service.hystrix;

import com.bank.feigncustomerconsumer.service.ICustomerGetAccountsService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.stereotype.Component;

@Component
public class CustomerGetAccountsHystrix implements ICustomerGetAccountsService {
    @Override
    public BankResult getAccounts(String custId) {
        return null;
    }
}
