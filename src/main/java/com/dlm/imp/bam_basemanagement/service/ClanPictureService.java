package com.dlm.imp.bam_basemanagement.service;

import java.util.List;

import com.dlm.imp.pojo.ClanPicture;

public interface ClanPictureService {
	List<ClanPicture> list();
	void add(ClanPicture record);
	void update(ClanPicture record);
	void delete(int id);
	ClanPicture get(int id);
	
	void addClanPicture(ClanPicture record);
	List<ClanPicture> findClanPictureListByVolumeId(int volumeId);
	
	void addClanPictureByVolumeIdAndAddress(int volumeId, List<String> uploadImagePathList);
}
