package com.dlm.imp.bam_basemanagement.vo;


import java.util.Map;

import com.dlm.imp.constant.CommonConstant;
import com.dlm.imp.util.Page;


public class RequestEntity {
	private Page page;
	private String surname;
	private String familyIdAndFamilyName;
	private Integer volumeId;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFamilyIdAndFamilyName() {
		return familyIdAndFamilyName;
	}

	public void setFamilyIdAndFamilyName(String familyIdAndFamilyName) {
		this.familyIdAndFamilyName = familyIdAndFamilyName;
	}

	public Integer getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(Integer volumeId) {
		this.volumeId = volumeId;
	}
}
