package com.bank.LoanService.dao;

import com.bank.LoanService.pojo.BankAccount;
import com.bank.LoanService.pojo.BankAccountExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BankAccountMapper {
    int countByExample(BankAccountExample example);

    int deleteByExample(BankAccountExample example);

    int deleteByPrimaryKey(String account);

    int insert(BankAccount record);

    int insertSelective(BankAccount record);

    List<BankAccount> selectByExample(BankAccountExample example);

    BankAccount selectByPrimaryKey(String account);

    int updateByExampleSelective(@Param("record") BankAccount record, @Param("example") BankAccountExample example);

    int updateByExample(@Param("record") BankAccount record, @Param("example") BankAccountExample example);

    int updateByPrimaryKeySelective(BankAccount record);

    int updateByPrimaryKey(BankAccount record);
}