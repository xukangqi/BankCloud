package com.bank.ServiceTransfer.dao;

import com.bank.ServiceTransfer.pojo.BankTransferLog;
import com.bank.ServiceTransfer.pojo.BankTransferLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BankTransferLogMapper {
    int countByExample(BankTransferLogExample example);

    int deleteByExample(BankTransferLogExample example);

    int deleteByPrimaryKey(String transferId);

    int insert(BankTransferLog record);

    int insertSelective(BankTransferLog record);

    List<BankTransferLog> selectByExample(BankTransferLogExample example);

    BankTransferLog selectByPrimaryKey(String transferId);

    int updateByExampleSelective(@Param("record") BankTransferLog record, @Param("example") BankTransferLogExample example);

    int updateByExample(@Param("record") BankTransferLog record, @Param("example") BankTransferLogExample example);

    int updateByPrimaryKeySelective(BankTransferLog record);

    int updateByPrimaryKey(BankTransferLog record);
}
