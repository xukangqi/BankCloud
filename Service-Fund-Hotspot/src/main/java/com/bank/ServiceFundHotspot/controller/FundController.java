package com.bank.ServiceFundHotspot.controller;

import com.bank.ServiceFundHotspot.utils.BankResult;
import com.bank.ServiceFundHotspot.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FundController {

    @Autowired
    private FundService fundService;

    @RequestMapping(value = "/query/product", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getFundProducts() {
        // service层操作
        BankResult bankResult = fundService.getFundProducts();

        return bankResult;
    }
    // 请求格式/query/productdetail?fundId=123&purchaseDate=123
    @RequestMapping(value = "/query/productdetail", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getFundOneProduct(@RequestParam(value = "fundId") String fundId) {
        // service层操作
        BankResult bankResult = fundService.getOneFundProduct(fundId);

        return bankResult;
    }
}
