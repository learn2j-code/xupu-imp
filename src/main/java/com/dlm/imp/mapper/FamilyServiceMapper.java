package com.dlm.imp.mapper;

import com.dlm.imp.pojo.FamilyService;
import com.dlm.imp.pojo.FamilyServiceExample;
import java.util.List;

public interface FamilyServiceMapper {
    int countByExample(FamilyServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FamilyService record);

    int insertSelective(FamilyService record);

    List<FamilyService> selectByExample(FamilyServiceExample example);

    FamilyService selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FamilyService record);

    int updateByPrimaryKey(FamilyService record);
}