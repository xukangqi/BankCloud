package com.bank.LoanService.dao;

import com.bank.LoanService.pojo.BankLoanPayment;
import com.bank.LoanService.pojo.BankLoanPaymentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BankLoanPaymentMapper {
    int countByExample(BankLoanPaymentExample example);

    int deleteByExample(BankLoanPaymentExample example);

    int deleteByPrimaryKey(String paymentId);

    int insert(BankLoanPayment record);

    int insertSelective(BankLoanPayment record);

    List<BankLoanPayment> selectByExample(BankLoanPaymentExample example);

    BankLoanPayment selectByPrimaryKey(String paymentId);

    int updateByExampleSelective(@Param("record") BankLoanPayment record, @Param("example") BankLoanPaymentExample example);

    int updateByExample(@Param("record") BankLoanPayment record, @Param("example") BankLoanPaymentExample example);

    int updateByPrimaryKeySelective(BankLoanPayment record);

    int updateByPrimaryKey(BankLoanPayment record);
}