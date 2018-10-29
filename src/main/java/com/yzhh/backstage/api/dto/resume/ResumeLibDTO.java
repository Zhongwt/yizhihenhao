package com.yzhh.backstage.api.dto.resume;

import io.swagger.annotations.ApiModelProperty;

public class ResumeLibDTO {

	@ApiModelProperty(value="简历id")
	private Long id;
	@ApiModelProperty(value="简历名称")
	private String name;
	@ApiModelProperty(value="用户头像")
	private String imageUrl;
	@ApiModelProperty(value="简历所有人名称")
	private String jobSeekerName;
	@ApiModelProperty(value="城市")
	private String city;
	@ApiModelProperty(value="性别")
	private String sex;
	@ApiModelProperty(value="周工作几天")
	private String workDay;
	@ApiModelProperty(value="工作时间/实习时间")
	private String internshipTime;
	@ApiModelProperty(value="到岗时间")
	private String arrivalDay;
	@ApiModelProperty(value="毕业学校")
	private String graduationSchool;
	@ApiModelProperty(value="学历")
	private String education;
	@ApiModelProperty(value="毕业时间")
	private String graduationTime;
	@ApiModelProperty(value="期望职位")
	private String wishPositionName;
	
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
	public String getJobSeekerName() {
		return jobSeekerName;
	}
	public void setJobSeekerName(String jobSeekerName) {
		this.jobSeekerName = jobSeekerName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getInternshipTime() {
		return internshipTime;
	}
	public void setInternshipTime(String internshipTime) {
		this.internshipTime = internshipTime;
	}
	public String getArrivalDay() {
		return arrivalDay;
	}
	public void setArrivalDay(String arrivalDay) {
		this.arrivalDay = arrivalDay;
	}
	public String getGraduationSchool() {
		return graduationSchool;
	}
	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getWishPositionName() {
		return wishPositionName;
	}
	public void setWishPositionName(String wishPositionName) {
		this.wishPositionName = wishPositionName;
	}
}
