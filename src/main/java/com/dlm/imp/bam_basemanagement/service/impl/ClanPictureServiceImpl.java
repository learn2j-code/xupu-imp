package com.dlm.imp.bam_basemanagement.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlm.imp.bam_basemanagement.service.ClanPictureService;
import com.dlm.imp.bam_basemanagement.service.ServiceCenterService;
import com.dlm.imp.bam_basemanagement.vo.NameAndAddress;
import com.dlm.imp.mapper.ClanBookMapper;
import com.dlm.imp.mapper.ClanPictureMapper;
import com.dlm.imp.mapper.ClanVolumeMapper;
import com.dlm.imp.pojo.ClanBook;
import com.dlm.imp.pojo.ClanBookExample;
import com.dlm.imp.pojo.ClanPicture;
import com.dlm.imp.pojo.ClanPictureExample;
import com.dlm.imp.pojo.ClanVolume;
import com.dlm.imp.pojo.ClanVolumeExample;
import com.dlm.imp.util.StringSortUtil;

@Service
public class ClanPictureServiceImpl implements ClanPictureService {
	@Autowired
	ClanPictureMapper mapper;
	
	@Override
	public List<ClanPicture> list() {
		ClanPictureExample example = new ClanPictureExample();
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}

	@Override
	public void add(ClanPicture record) {
		mapper.insertSelective(record);
	}

	@Override
	public void delete(int id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(ClanPicture record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ClanPicture get(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ClanPicture> findClanPictureListByVolumeId(int volumeId) {
		ClanPictureExample example = new ClanPictureExample();
		example.setOrderByClause("id desc");
		example.createCriteria().andVolumeIdEqualTo(volumeId);
		return mapper.selectByExample(example);
	}

	@Override
	public void addClanPicture(ClanPicture record) {
		
		add(record);
	}

	@Override
	public void addClanPictureByVolumeIdAndAddress(int volumeId, List<String> uploadImagePathList) {
		//先删除该卷下所有的图片地址
		List<ClanPicture> clanPictureList = findClanPictureListByVolumeId(volumeId);
		for(ClanPicture clanPicture:clanPictureList) {
			delete(clanPicture.getId());
		}
		// 先截取字符串，按名字排序
		int i=0;
		List<NameAndAddress> nameAndAddressList = new ArrayList<NameAndAddress>();
		for(String uploadImagePath:uploadImagePathList) {
			String imagePath = uploadImagePath.substring(uploadImagePath.indexOf("imp"));
			String imageName = uploadImagePath.substring(uploadImagePath.lastIndexOf("\\"));
			
			NameAndAddress nameAndAddress = new NameAndAddress();
			nameAndAddress.setAddress(imagePath);
			nameAndAddress.setName(imageName);
			nameAndAddressList.add(nameAndAddress);
			nameAndAddressList.sort(Comparator.naturalOrder());//正序比较
		}
		
		for(NameAndAddress nameAndAddress:nameAndAddressList) {
			ClanPicture record = new ClanPicture();
			record.setVolumeId(volumeId);
			record.setPictureAddress(nameAndAddress.getAddress());
			record.setPictureName(nameAndAddress.getName());
			record.setPictureNo(i++);
			// 接着保存到数据库
			add(record);
		}
		
	}

}
