package com.bank.feignconsumer1.service;


import com.bank.feignconsumer1.service.hystrix.CustomerServiceHystrix;
import com.bank.feignconsumer1.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@FeignClient(value = "bankuser-service-provider", fallback = CustomerServiceHystrix.class)
public interface ICustomerService {
    @RequestMapping(value = "customer/all", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getAllCustomer();
}
