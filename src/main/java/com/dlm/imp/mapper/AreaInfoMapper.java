package com.dlm.imp.mapper;

import com.dlm.imp.pojo.AreaInfo;
import com.dlm.imp.pojo.AreaInfoExample;
import java.util.List;

public interface AreaInfoMapper {
    int countByExample(AreaInfoExample example);

    int deleteByPrimaryKey(Integer areaId);

    int insert(AreaInfo record);

    int insertSelective(AreaInfo record);

    List<AreaInfo> selectByExample(AreaInfoExample example);

    AreaInfo selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(AreaInfo record);

    int updateByPrimaryKey(AreaInfo record);
}