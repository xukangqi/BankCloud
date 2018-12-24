package com.bank.LoanService.dao;

import com.bank.LoanService.pojo.BankLoan;
import com.bank.LoanService.pojo.BankLoanExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BankLoanMapper {
    int countByExample(BankLoanExample example);

    int deleteByExample(BankLoanExample example);

    int deleteByPrimaryKey(String transId);

    int insert(BankLoan record);

    int insertSelective(BankLoan record);

    List<BankLoan> selectByExample(BankLoanExample example);

    BankLoan selectByPrimaryKey(String transId);

    int updateByExampleSelective(@Param("record") BankLoan record, @Param("example") BankLoanExample example);

    int updateByExample(@Param("record") BankLoan record, @Param("example") BankLoanExample example);

    int updateByPrimaryKeySelective(BankLoan record);

    int updateByPrimaryKey(BankLoan record);
}