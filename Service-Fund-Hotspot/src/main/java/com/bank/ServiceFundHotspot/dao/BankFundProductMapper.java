package com.bank.ServiceFundHotspot.dao;

import com.bank.ServiceFundHotspot.pojo.BankFundProduct;
import com.bank.ServiceFundHotspot.pojo.BankFundProductExample;
import com.bank.ServiceFundHotspot.pojo.BankFundProductKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BankFundProductMapper {
    int countByExample(BankFundProductExample example);

    int deleteByExample(BankFundProductExample example);

    int deleteByPrimaryKey(BankFundProductKey key);

    int insert(BankFundProduct record);

    int insertSelective(BankFundProduct record);

    List<BankFundProduct> selectByExample(BankFundProductExample example);

    BankFundProduct selectByPrimaryKey(BankFundProductKey key);

    int updateByExampleSelective(@Param("record") BankFundProduct record, @Param("example") BankFundProductExample example);

    int updateByExample(@Param("record") BankFundProduct record, @Param("example") BankFundProductExample example);

    int updateByPrimaryKeySelective(BankFundProduct record);

    int updateByPrimaryKey(BankFundProduct record);
}
