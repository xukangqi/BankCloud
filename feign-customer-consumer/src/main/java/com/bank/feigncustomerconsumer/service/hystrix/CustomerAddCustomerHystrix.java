package com.bank.feigncustomerconsumer.service.hystrix;

import com.bank.feigncustomerconsumer.pojo.BankCustomer;
import com.bank.feigncustomerconsumer.service.ICustomerAddCustomerService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddCustomerHystrix implements ICustomerAddCustomerService {
    @Override
    public BankResult addCustomer(BankCustomer customer) {
        return null;
    }
}
