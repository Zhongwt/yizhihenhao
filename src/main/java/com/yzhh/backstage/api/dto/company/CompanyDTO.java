package com.yzhh.backstage.api.dto.company;

import javax.validation.constraints.NotNull;

public class CompanyDTO {

	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String city;
	private String joinDate;
	private String status;
	@NotNull
	private String address;
	@NotNull
	private String field;				//领域
	@NotNull
	private String scale;				//规模
	
	private String website;			//公司网站
	@NotNull
	private String email;			//
	private String note;				//
	private String description;	//
	private String phone;			//
	private String attachent;		//
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAttachent() {
		return attachent;
	}
	public void setAttachent(String attachent) {
		this.attachent = attachent;
	}
	
}
