package com.dlm.imp.bam_basemanagement.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlm.imp.bam_basemanagement.service.FamilyInfoService;
import com.dlm.imp.mapper.FamilyInfoMapper;
import com.dlm.imp.pojo.FamilyInfo;
import com.dlm.imp.pojo.FamilyInfoExample;
import com.dlm.imp.pojo.FamilyInfoWithBLOBs;

@Service
public class FamilyInfoServiceImpl implements FamilyInfoService {
	@Autowired
	FamilyInfoMapper mapper;

	@Override
	public List<FamilyInfo> list() {
		FamilyInfoExample example = new FamilyInfoExample();
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}

	@Override
	public void add(FamilyInfoWithBLOBs record) {
		mapper.insertSelective(record);
	}

	@Override
	public void delete(int id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(FamilyInfoWithBLOBs record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public FamilyInfoWithBLOBs get(int id) {
		return mapper.selectByPrimaryKey(id);
	}

}
