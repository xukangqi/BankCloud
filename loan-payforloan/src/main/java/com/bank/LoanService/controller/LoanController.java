package com.bank.LoanService.controller;

import com.bank.LoanService.pojo.loan.BankLoanPaymentInfo;
import com.bank.LoanService.service.LoanService;
import com.bank.LoanService.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;


    //还款信息登记
    @RequestMapping(value = "/payment",method = RequestMethod.POST)
    @ResponseBody
    public BankResult payForLoan(BankLoanPaymentInfo bankLoanPaymentInfo) {
        return loanService.payForLoan(bankLoanPaymentInfo);
    }

    //获取还款日志
    @RequestMapping(value = "/paylog/{value}",method = RequestMethod.GET)
    @ResponseBody
    public BankResult getPayLog(@PathVariable("value")String value) {
        return loanService.getPaylog(value);
    }
}
