package com.yzhh.backstage.api.dto.resume;

public class ResumeSearchDTO {

	public String type;
	private String searchKey;
	private String city;
	private Integer workDay;
	private String arrayDay;
	private String education;
	private String graduationTime;
	private String sex;
	private String status;
	private Long companyId;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getWorkDay() {
		return workDay;
	}
	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}
	public String getArrayDay() {
		return arrayDay;
	}
	public void setArrayDay(String arrayDay) {
		this.arrayDay = arrayDay;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
