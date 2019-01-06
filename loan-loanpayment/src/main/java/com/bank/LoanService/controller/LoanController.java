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
    public BankResult insertLoanPayment(@RequestBody BankLoanPayment bankLoan) {
        return loanService.insertData(bankLoan);
    }

    //传入transId,执行还款操作
    @RequestMapping(value = "/payforLoan/{transId}/{amount}/{account}/{amountInAccount}",method = RequestMethod.GET)
    @ResponseBody
    public BankResult payforLoan(@PathVariable("transId")String transId,@PathVariable("amount")String amount,
                                 @PathVariable("account")String account,@PathVariable("amountInAccount")Double amountInAccount) {
        return loanService.payforLoan(transId,amount,account,amountInAccount);
    }

    //还款信息获取
    @RequestMapping(value = "/payment/{transId}",method = RequestMethod.GET)
    @ResponseBody
    public BankResult getPaymentOneInfo(@PathVariable("transId")String transId) {
        return loanService.getPaymentOneInfo(transId);
    }
}
