package com.bank.feignuserconsumer.service.hystrix;

import com.bank.feignuserconsumer.pojo.BankUser;
import com.bank.feignuserconsumer.service.IUserLoginService;
import com.bank.feignuserconsumer.utils.BankResult;
import org.springframework.stereotype.Component;

@Component
public class UserLoginHystrix implements IUserLoginService {
    @Override
    public BankResult login(String username, String password) {
        return new BankResult("sorry, error occured.");
    }

}
