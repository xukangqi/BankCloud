package com.bank.LoanService.controller;

import com.bank.LoanService.pojo.BankLoanPayment;
import com.bank.LoanService.pojo.BankLoanPaymentExample;
import com.bank.LoanService.service.LoanService;
import com.bank.LoanService.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    //数据插入
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public BankResult insertLoanPayment(BankLoanPayment bankLoan) {
        return loanService.insertData(bankLoan);
    }

    //更新数据
    @RequestMapping(value = "/updateByEntity",method = RequestMethod.POST)
    @ResponseBody
    public BankResult UpdateLoanPayment(BankLoanPayment bankLoan) {
        return loanService.updateByPrimaryKeySelective(bankLoan);
    }

    //查询数据by example
    @RequestMapping(value = "/selectByExample",method = RequestMethod.POST)
    @ResponseBody
    public BankResult selectByExa(BankLoanPaymentExample bankLoanPaymentExample) {
        return loanService.selectByExample(bankLoanPaymentExample);
    }

    //还款信息获取
    @RequestMapping(value = "/payment/{transId}",method = RequestMethod.GET)
    @ResponseBody
    public BankResult getPaymentOneInfo(@PathVariable("transId")String transId) {
        return loanService.getPaymentOneInfo(transId);
    }
}
