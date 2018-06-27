package com.yzhh.backstage.api.entity;

/**
 * @description:公司
 * @projectName:backstage-api
 * @className:Company.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:08:52
 * @version 1.0.1
 */
public class Company extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String name;				//
    private String city;					//
    private String address;				//
    private Long joinDate;				//
    private Integer status;				//
    private Integer isNumber;		//
    private String description;		//
    private String field;					//
    private String scale;					//
    private String website;				//
    private String note;					//
    private String email;				//
    private String attachment;		//
    private Integer identification;	//

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Long joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsNumber() {
        return isNumber;
    }

    public void setIsNumber(Integer isNumber) {
        this.isNumber = isNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }
}