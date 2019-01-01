package com.bank.feigncustomerconsumer.service.hystrix;

import com.bank.feigncustomerconsumer.pojo.BankAccount;
import com.bank.feigncustomerconsumer.pojo.BankCustomer;
import com.bank.feigncustomerconsumer.service.ICustomerOtherService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.stereotype.Component;


@Component
public class CustomerOtherHystrix implements ICustomerOtherService {
    @Override
    public BankResult getAllCustomer() {
        return null;
    }


    @Override
    public BankResult updateAccount(BankCustomer customer) {
        return null;
    }


    @Override
    public BankResult deleteAccount(String account) {
        return null;
    }
}
