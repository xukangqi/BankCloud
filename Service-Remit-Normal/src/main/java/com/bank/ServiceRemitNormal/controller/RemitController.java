package com.bank.ServiceRemitNormal.controller;


import com.bank.ServiceRemitNormal.service.RemitService;
import com.bank.ServiceRemitNormal.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user/remit")
public class RemitController {
    @Autowired
    private RemitService remitService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public BankResult createRemit(@RequestParam(value = "name")String name,
                                  @RequestParam(value = "phone")String phone,
                                  @RequestParam(value = "remitOutAccount")String remitOutAccount,
                                  @RequestParam(value = "remitInAccount")String remitInAccount,
                                  @RequestParam(value = "password")String password,
                                  @RequestParam(value = "amount")double amount) {
        BankResult bankResult = remitService.createRemit(name, phone, remitOutAccount, remitInAccount, password, amount);
        return bankResult;
    }
}
