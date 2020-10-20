package com.dlm.imp.bam_basemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlm.imp.bam_basemanagement.service.AreaInfoService;
import com.dlm.imp.bam_basemanagement.service.ServiceCenterService;
import com.dlm.imp.mapper.AreaInfoMapper;
import com.dlm.imp.mapper.ServiceCenterMapper;
import com.dlm.imp.pojo.AreaInfo;
import com.dlm.imp.pojo.AreaInfoExample;
import com.dlm.imp.pojo.ServiceCenter;
import com.dlm.imp.pojo.ServiceCenterExample;

@Service
public class ServiceCenterServiceImpl implements ServiceCenterService {
	@Autowired
	ServiceCenterMapper mapper;

	@Override
	public List<ServiceCenter> list() {
		ServiceCenterExample example = new ServiceCenterExample();
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}

	@Override
	public void add(ServiceCenter record) {
		mapper.insertSelective(record);
	}

	@Override
	public void delete(int id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(ServiceCenter record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ServiceCenter get(int id) {
		return mapper.selectByPrimaryKey(id);
	}

}
