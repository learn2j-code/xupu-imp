package com.dlm.imp.pojo;

import java.util.Date;

public class ClanBook {
    private Integer id;

    private Integer familyId;

    private String surname;

    private String bookName;

    private Integer volumeNum;

    private String coverImage;

    private Integer dealFlag;

    private Integer status;

    private Integer relationModifyFlag;

    private Integer contentModifyFlag;

    private Integer ancestorId;

    private String ancestorName;

    private Integer bookAncestorId;

    private String bookAncestorName;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname == null ? null : surname.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public Integer getVolumeNum() {
        return volumeNum;
    }

    public void setVolumeNum(Integer volumeNum) {
        this.volumeNum = volumeNum;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }

    public Integer getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(Integer dealFlag) {
        this.dealFlag = dealFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRelationModifyFlag() {
        return relationModifyFlag;
    }

    public void setRelationModifyFlag(Integer relationModifyFlag) {
        this.relationModifyFlag = relationModifyFlag;
    }

    public Integer getContentModifyFlag() {
        return contentModifyFlag;
    }

    public void setContentModifyFlag(Integer contentModifyFlag) {
        this.contentModifyFlag = contentModifyFlag;
    }

    public Integer getAncestorId() {
        return ancestorId;
    }

    public void setAncestorId(Integer ancestorId) {
        this.ancestorId = ancestorId;
    }

    public String getAncestorName() {
        return ancestorName;
    }

    public void setAncestorName(String ancestorName) {
        this.ancestorName = ancestorName == null ? null : ancestorName.trim();
    }

    public Integer getBookAncestorId() {
        return bookAncestorId;
    }

    public void setBookAncestorId(Integer bookAncestorId) {
        this.bookAncestorId = bookAncestorId;
    }

    public String getBookAncestorName() {
        return bookAncestorName;
    }

    public void setBookAncestorName(String bookAncestorName) {
        this.bookAncestorName = bookAncestorName == null ? null : bookAncestorName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}