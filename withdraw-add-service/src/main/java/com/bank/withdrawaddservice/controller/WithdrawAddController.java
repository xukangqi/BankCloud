package com.bank.withdrawaddservice.controller;

import com.bank.withdrawaddservice.pojo.BankWithdraw;
import com.bank.withdrawaddservice.service.WithdrawAddService;
import com.bank.withdrawaddservice.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "/withdraw")
public class WithdrawAddController {

    @Autowired
    private WithdrawAddService withdrawAddService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BankResult add(@RequestParam("password")String password, BankWithdraw bankWithdraw){
        return withdrawAddService.insert(bankWithdraw, password);
    }
}
