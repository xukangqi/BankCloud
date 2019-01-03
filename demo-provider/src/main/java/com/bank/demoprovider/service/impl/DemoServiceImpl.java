package com.bank.demoprovider.service.impl;

import com.bank.demoprovider.dao.BankUserMapper;
import com.bank.demoprovider.pojo.BankUser;
import com.bank.demoprovider.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DemoServiceImpl  implements DemoService {

    @Autowired
    private BankUserMapper bankUserMapper;

    @Override
    public String hello(){
        waitForTest();
        return "hello";
    }

    public void waitForTest(){
        Random random=new Random();
        int result=random.nextInt(10);
        if(result<5){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public BankUser test() {
        return  bankUserMapper.selectByPrimaryKey("18805862675");
    }
}
