package com.bank.withdrawaddservice.dao;


import com.bank.withdrawaddservice.pojo.BankDeposit;
import com.bank.withdrawaddservice.pojo.BankDepositExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BankDepositMapper {
    int countByExample(BankDepositExample example);

    int deleteByExample(BankDepositExample example);

    int deleteByPrimaryKey(String depositId);

    int insert(BankDeposit record);

    int insertSelective(BankDeposit record);

    List<BankDeposit> selectByExample(BankDepositExample example);

    BankDeposit selectByPrimaryKey(String depositId);

    int updateByExampleSelective(@Param("record") BankDeposit record, @Param("example") BankDepositExample example);

    int updateByExample(@Param("record") BankDeposit record, @Param("example") BankDepositExample example);

    int updateByPrimaryKeySelective(BankDeposit record);

    int updateByPrimaryKey(BankDeposit record);
}