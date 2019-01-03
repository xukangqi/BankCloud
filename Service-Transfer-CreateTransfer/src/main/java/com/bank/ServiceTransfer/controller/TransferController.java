package com.bank.ServiceTransfer.controller;

import com.bank.ServiceTransfer.service.TransferService;
import com.bank.ServiceTransfer.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/transfer")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public BankResult createTransfer(@RequestParam(value = "name")String name,
                                     @RequestParam(value = "phone")String phone,
                                     @RequestParam(value = "transferOutAccount")String transferOutAccount,
                                     @RequestParam(value = "transferInAccount")String transferInAccount,
                                     @RequestParam(value = "password")String password,
                                     @RequestParam(value = "amount")double amount) {
        BankResult bankResult = transferService.createTransfer(name, phone, transferOutAccount, transferInAccount, password, amount);

        return bankResult;
    }
}
