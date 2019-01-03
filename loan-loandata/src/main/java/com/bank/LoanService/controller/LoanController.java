package com.bank.LoanService.controller;

import com.bank.LoanService.pojo.BankLoan;
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
    public BankResult insertLoan(BankLoan bankLoan) {
        return loanService.insertData(bankLoan);
    }

    //数据查询
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public BankResult getOneRecord(@PathVariable("id") String id) {
        return loanService.sentOneRecord(id);
    }

    //更新数据
    @RequestMapping(value = "/updateByEntity",method = RequestMethod.POST)
    @ResponseBody
    public BankResult UpdateLoan(BankLoan bankLoan) {
        return loanService.updateByPrimaryKeySelective(bankLoan);
    }

    //获取所有的贷款信息
    @RequestMapping(value = "/allRecord",method = RequestMethod.GET)
    @ResponseBody
    public BankResult getAllRecords() {
        return loanService.sentAllRecords();
    }
}
