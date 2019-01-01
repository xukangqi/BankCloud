package com.bank.feignuserconsumer.controller;

import com.bank.feignuserconsumer.pojo.BankUser;
import com.bank.feignuserconsumer.service.IUserOtherService;
import com.bank.feignuserconsumer.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/user")
public class UserOtherController {
    @Autowired
    private IUserOtherService userOtherService;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BankResult delete(@RequestParam(value = "username") String username) {
        return userOtherService.delete(username);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BankResult register(BankUser bankUser) {
        return userOtherService.register(bankUser);
    }

    @RequestMapping(value = "/changeinfo", method = RequestMethod.POST)
    @ResponseBody
    public BankResult changeinfo(BankUser user) {
        return userOtherService.changeinfo(user);
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    @ResponseBody
    public BankResult changepassword(@RequestParam("username") String username, @RequestParam("oldpassword") String oldpassword,
                                     @RequestParam("newpassword") String newpassword) {
        return userOtherService.changepassword(username, oldpassword, newpassword);
    }
}
