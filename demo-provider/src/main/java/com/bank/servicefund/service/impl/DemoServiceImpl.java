package com.bank.servicefund.service.impl;

import com.bank.servicefund.dao.BankUserMapper;
import com.bank.servicefund.pojo.BankUser;
import com.bank.servicefund.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private BankUserMapper bankUserMapper;

    @Override
    public String hello() {
        return "武汉市吧、";
    }

    @Override
    public BankUser test() {
        return bankUserMapper.selectByPrimaryKey("18805862675");
    }
}
