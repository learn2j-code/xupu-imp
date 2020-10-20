package com.dlm.imp.mapper;

import com.dlm.imp.pojo.ServiceCenter;
import com.dlm.imp.pojo.ServiceCenterExample;
import java.util.List;

public interface ServiceCenterMapper {
    int countByExample(ServiceCenterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceCenter record);

    int insertSelective(ServiceCenter record);

    List<ServiceCenter> selectByExample(ServiceCenterExample example);

    ServiceCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceCenter record);

    int updateByPrimaryKey(ServiceCenter record);
}