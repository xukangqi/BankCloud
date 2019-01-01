package com.bank.feignuserconsumer.service;

import com.bank.feignuserconsumer.pojo.BankUser;
import com.bank.feignuserconsumer.service.hystrix.UserLoginHystrix;
import com.bank.feignuserconsumer.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "provider-user-login", fallback = UserLoginHystrix.class)
//@RequestMapping("/user")   此写法报错
public interface IUserLoginService {
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public BankResult login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);

}
