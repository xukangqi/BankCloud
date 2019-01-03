package com.bank.LoanService.controller;

import com.bank.LoanService.pojo.loan.BankLoanApplyInfo;
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

    //贷款申请
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    @ResponseBody
    public BankResult applyLoan(BankLoanApplyInfo bankLoanApplyInfo) {
        System.out.println(bankLoanApplyInfo.toString());
        return loanService.dealApplyment(bankLoanApplyInfo);
    }

    //获取利率信息
    @RequestMapping(value = "/interestRate",method = RequestMethod.GET)
    @ResponseBody
    public BankResult getInterestRate() {
        return loanService.getInterestRate();
    }

    //获取利率信息
    @RequestMapping(value = "/interestRate/{value}",method = RequestMethod.GET)
    @ResponseBody
    public BankResult getInterestOneRate(@PathVariable("value")int value) {
        return loanService.getInterestOneRate(value);
    }
}
