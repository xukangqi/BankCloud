package com.bank.ServiceFundNormal.dao;

import com.bank.ServiceFundNormal.pojo.BankFundHold;
import com.bank.ServiceFundNormal.pojo.BankFundHoldExample;
import com.bank.ServiceFundNormal.pojo.BankFundHoldKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BankFundHoldMapper {
    int countByExample(BankFundHoldExample example);

    int deleteByExample(BankFundHoldExample example);

    int deleteByPrimaryKey(BankFundHoldKey key);

    int insert(BankFundHold record);

    int insertSelective(BankFundHold record);

    List<BankFundHold> selectByExample(BankFundHoldExample example);

    BankFundHold selectByPrimaryKey(BankFundHoldKey key);

    int updateByExampleSelective(@Param("record") BankFundHold record, @Param("example") BankFundHoldExample example);

    int updateByExample(@Param("record") BankFundHold record, @Param("example") BankFundHoldExample example);

    int updateByPrimaryKeySelective(BankFundHold record);

    int updateByPrimaryKey(BankFundHold record);
}
