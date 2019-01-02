package com.bank.servicefund.dao;

import com.bank.servicefund.pojo.BankUser;
import com.bank.servicefund.pojo.BankUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BankUserMapper {
    int countByExample(BankUserExample example);

    int deleteByExample(BankUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(BankUser record);

    int insertSelective(BankUser record);

    List<BankUser> selectByExample(BankUserExample example);

    BankUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") BankUser record, @Param("example") BankUserExample example);

    int updateByExample(@Param("record") BankUser record, @Param("example") BankUserExample example);

    int updateByPrimaryKeySelective(BankUser record);

    int updateByPrimaryKey(BankUser record);
}
