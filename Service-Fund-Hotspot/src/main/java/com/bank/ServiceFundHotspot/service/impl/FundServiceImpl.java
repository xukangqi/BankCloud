package com.bank.ServiceFundHotspot.service.impl;

import com.bank.ServiceFundHotspot.dao.*;
import com.bank.ServiceFundHotspot.pojo.*;
import com.bank.ServiceFundHotspot.utils.BankResult;
import com.bank.ServiceFundHotspot.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.List;

@Service
public class FundServiceImpl implements FundService {

    @Autowired
    private BankFundProductMapper bankFundProductMapper;
    @Override
    @HystrixCommand(fallbackMethod = "errorMethodInFund",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public BankResult getFundProducts() {
        BankFundProductExample bankFundProductExample = new BankFundProductExample();
        BankFundProductExample.Criteria criteria = bankFundProductExample.createCriteria();
        criteria.andFundIdIsNotNull();
        List<BankFundProduct> bankFundProductList = bankFundProductMapper.selectByExample(bankFundProductExample);
        return BankResult.ok(bankFundProductList);
    }
    @Override
    @HystrixCommand(fallbackMethod = "errorMethodInFund",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public BankResult getOneFundProduct(String fundId) {
        BankFundProductExample bankFundProductExample = new BankFundProductExample();
        BankFundProductExample.Criteria criteria = bankFundProductExample.createCriteria();
        criteria.andFundIdEqualTo(fundId);
        List<BankFundProduct> bankFundProductList = bankFundProductMapper.selectByExample(bankFundProductExample);
        return BankResult.ok(bankFundProductList);
    }

    public BankResult errorMethodInFund(){
        return BankResult.build(400, "Hystrix in ServiceFundHotspot.", "");
    }
}
