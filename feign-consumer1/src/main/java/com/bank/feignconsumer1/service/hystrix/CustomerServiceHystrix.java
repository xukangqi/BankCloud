package com.bank.feignconsumer1.service.hystrix;

import com.bank.feignconsumer1.service.ICustomerService;
import com.bank.feignconsumer1.utils.BankResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomerServiceHystrix implements ICustomerService {
    @Override
    public BankResult getAllCustomer() {
        return new BankResult("sorry, error occured.");
    }
}
