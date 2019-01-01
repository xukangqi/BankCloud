package com.bank.feigncustomerconsumer.service.hystrix;

import com.bank.feigncustomerconsumer.service.ICustomerGetOneService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.stereotype.Component;

@Component
public class CustomerGetOneHystrix implements ICustomerGetOneService {
    @Override
    public BankResult getOneCustomer(String custID) {
        return null;
    }
}
