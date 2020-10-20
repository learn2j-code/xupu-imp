package com.dlm.imp.bam_basemanagement.vo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.dlm.imp.pojo.ClanPicture;
import com.dlm.imp.util.StringSortUtil;

// 
public class NameAndAddress implements Comparable<NameAndAddress> {
	private String name;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public int compareTo(NameAndAddress user) {//重写Comparable接口的compareTo方法，
		return StringSortUtil.compareTo(this.getName(), user.getName());
	}	
	
	
	public static void main(String[] args) {
		List<String> uploadImagePathList = new ArrayList<String>();
		String k1 = "\\imp\\uploadClanPictures\\null\\null20181128\\IMG_1.JPG";
		String k2 = "\\imp\\uploadClanPictures\\null\\null20181128\\IMG_11.JPG";
		String k3 = "\\imp\\uploadClanPictures\\null\\null20181128\\IMG_2.JPG";
		String k4 = "\\imp\\uploadClanPictures\\null\\null20181128\\IMG_20.JPG";
		uploadImagePathList.add(k1);
		uploadImagePathList.add(k2);
		uploadImagePathList.add(k3);
		uploadImagePathList.add(k4);
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
			System.out.println(nameAndAddress.getAddress());
			System.out.println(nameAndAddress.getName());
		}
	}
}
