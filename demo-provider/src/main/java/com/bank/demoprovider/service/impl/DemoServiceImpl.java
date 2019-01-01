package com.bank.demoprovider.service.impl;

import com.bank.demoprovider.dao.BankUserMapper;
import com.bank.demoprovider.pojo.BankUser;
import com.bank.demoprovider.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl  implements DemoService {

    @Autowired
    private BankUserMapper bankUserMapper;

    @Override
    public String hello(){
        return "hello";
    }

    @Override
    public BankUser test() {
        return  bankUserMapper.selectByPrimaryKey("18805862675");
    }
}
