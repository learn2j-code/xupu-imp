package com.dlm.imp.mapper;

import com.dlm.imp.pojo.FamilyInfo;
import com.dlm.imp.pojo.FamilyInfoExample;
import com.dlm.imp.pojo.FamilyInfoWithBLOBs;
import java.util.List;

public interface FamilyInfoMapper {
    int countByExample(FamilyInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FamilyInfoWithBLOBs record);

    int insertSelective(FamilyInfoWithBLOBs record);

    List<FamilyInfoWithBLOBs> selectByExampleWithBLOBs(FamilyInfoExample example);

    List<FamilyInfo> selectByExample(FamilyInfoExample example);

    FamilyInfoWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FamilyInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FamilyInfoWithBLOBs record);

    int updateByPrimaryKey(FamilyInfo record);
}