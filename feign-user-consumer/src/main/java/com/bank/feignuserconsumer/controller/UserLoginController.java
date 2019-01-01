package com.bank.feignuserconsumer.controller;

import com.bank.feignuserconsumer.service.IUserLoginService;
import com.bank.feignuserconsumer.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserLoginController {
    @Autowired
    private IUserLoginService userLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BankResult login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        return userLoginService.login(username, password);
    }

}
