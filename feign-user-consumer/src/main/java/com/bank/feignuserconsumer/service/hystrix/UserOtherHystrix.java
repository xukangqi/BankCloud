package com.bank.feignuserconsumer.service.hystrix;

import com.bank.feignuserconsumer.pojo.BankUser;
import com.bank.feignuserconsumer.service.IUserOtherService;
import com.bank.feignuserconsumer.utils.BankResult;
import org.springframework.stereotype.Component;

@Component
public class UserOtherHystrix implements IUserOtherService {

    @Override
    public BankResult delete(String username) {
        return new BankResult("sorry, error occured.");
    }

    @Override
    public BankResult register(BankUser bankUser) {
        return new BankResult("sorry, error occured.");
    }

    @Override
    public BankResult changeinfo(BankUser user) {
        return new BankResult("sorry, error occured.");
    }

    @Override
    public BankResult changepassword(String username, String oldpassword, String newpassword) {
        return new BankResult("sorry, error occured.");
    }
}
