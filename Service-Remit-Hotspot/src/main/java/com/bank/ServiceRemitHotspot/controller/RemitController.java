package com.bank.ServiceRemitHotspot.controller;


import com.bank.ServiceRemitHotspot.service.RemitService;
import com.bank.ServiceRemitHotspot.utils.BankResult;
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

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public BankResult getRemit(@RequestParam(value = "remitInAccount")String remitInAccount,
                               @RequestParam(value = "remitId")String remitId) {
        BankResult bankResult = remitService.getRemit(remitInAccount, remitId);
        return bankResult;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getRemitLogs() {
        BankResult bankResult = remitService.getRemitLogs();
        return bankResult;
    }

    @RequestMapping(value = "/query/{remitId}", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getOneRemitLog(@RequestParam(value = "id")String remitId) {
        BankResult bankResult = remitService.getOneRemitLog(remitId);
        return bankResult;
    }
}
