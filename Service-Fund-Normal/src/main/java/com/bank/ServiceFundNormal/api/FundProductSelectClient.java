package com.bank.ServiceFundNormal.api;

import com.bank.ServiceFundNormal.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ServiceFundHotspot")
public interface FundProductSelectClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/query/product",
            consumes = "application/json"
    )
    BankResult getFundProducts();
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/query/productdetail",
            consumes = "application/json"
    )
    BankResult getOneFundProduct(String fundId);
}
