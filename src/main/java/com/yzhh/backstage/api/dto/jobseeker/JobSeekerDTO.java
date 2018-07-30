package com.yzhh.backstage.api.dto.jobseeker;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class JobSeekerDTO {
	
	private Long id;
	@NotNull
	@ApiModelProperty(value = "名称")
	private String name;
	@NotNull
	@ApiModelProperty(value = "头像url")
	private String picUrl;
	@NotNull
	@ApiModelProperty(value = "性别")
	private String sex;
	@ApiModelProperty(value = "省")
	private String province;
	@NotNull
	@ApiModelProperty(value = "市")
	private String city;
	@ApiModelProperty(value = "区")
	private String area;
	@NotNull
	@ApiModelProperty(value = "生日")
	private String birthday;
	@ApiModelProperty(value = "学历")
	private String education;
	@ApiModelProperty(value = "毕业时间")
	private String graduationTime;
	@ApiModelProperty(value = "毕业学校")
	private String graduationSchool;
	@ApiModelProperty(value = "专业")
	private String major;
	@ApiModelProperty(value = "专业类型")
	private String majorType;
	@NotNull
	@ApiModelProperty(value = "电话")
	private String phone;
	@NotNull
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "个人备注")
	private String note;
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getGraduationTime() {
		return graduationTime;
	}
	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}
	public String getGraduationSchool() {
		return graduationSchool;
	}
	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMajorType() {
		return majorType;
	}
	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
