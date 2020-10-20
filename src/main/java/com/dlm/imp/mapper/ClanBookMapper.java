package com.dlm.imp.mapper;

import com.dlm.imp.pojo.ClanBook;
import com.dlm.imp.pojo.ClanBookExample;
import java.util.List;

public interface ClanBookMapper {
    int countByExample(ClanBookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClanBook record);

    int insertSelective(ClanBook record);

    List<ClanBook> selectByExample(ClanBookExample example);

    ClanBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClanBook record);

    int updateByPrimaryKey(ClanBook record);
}