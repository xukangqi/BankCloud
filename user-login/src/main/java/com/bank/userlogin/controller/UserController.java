package com.bank.userlogin.controller;

import com.bank.userlogin.pojo.BankUser;
import com.bank.userlogin.service.IUserService;
import com.bank.userlogin.utils.BankResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lujiafeng on 2018/10/14.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    // 用户登入 （已测试）
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BankResult login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        System.out.println(username);
        BankUser user = userService.login(username, password);
        if (user == null) {
            BankResult bankResult = new BankResult();
            String msg = "登录失败,请检查账户、密码是否正确";
            bankResult.setStatus(123);
            bankResult.setMsg(msg);
            return bankResult;
        } else {
            user.setPassword(null);
            return new BankResult(200, "ok", user);
        }
    }


}
