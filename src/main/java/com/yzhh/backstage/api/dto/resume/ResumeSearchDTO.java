package com.yzhh.backstage.api.dto.resume;

import io.swagger.annotations.ApiModelProperty;

public class ResumeSearchDTO {

	@ApiModelProperty(value = "")
	public String type;
	@ApiModelProperty(value = "职位名称")
	public String name;
	@ApiModelProperty(value = "搜索姓名或学校")
	private String searchKey;
	@ApiModelProperty(value = "城市")
	private String city;
	@ApiModelProperty(value = "实习时间")
	private String internshipTime;
	@ApiModelProperty(value = "工作时间")
	private Integer workDay;
	@ApiModelProperty(value = "到岗日期")
	private String arrayDay;
	@ApiModelProperty(value = "学历")
	private String education;
	@ApiModelProperty(value = "毕业时间")
	private String graduationTime;
	@ApiModelProperty(value = "毕业学校")
	private String graduationSchool;
	@ApiModelProperty(value = "简历性别")
	private String sex;
	@ApiModelProperty(value = "状态")
	private String status;
	@ApiModelProperty(value = "公司id")
	private Long companyId;
	@ApiModelProperty(value = "职位id")
	private Long positionId;
	@ApiModelProperty(value = "用户id")
	private Long jobSeekerId;
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
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public Long getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(Long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	public String getGraduationSchool() {
		return graduationSchool;
	}
	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
	}
	public String getInternshipTime() {
		return internshipTime;
	}
	public void setInternshipTime(String internshipTime) {
		this.internshipTime = internshipTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
