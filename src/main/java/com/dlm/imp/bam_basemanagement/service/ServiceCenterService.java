package com.dlm.imp.bam_basemanagement.service;

import java.util.List;

import com.dlm.imp.pojo.ServiceCenter;

public interface ServiceCenterService {
	List<ServiceCenter> list();
	void add(ServiceCenter record);
	void update(ServiceCenter record);
	void delete(int id);
	ServiceCenter get(int id);
	
}
