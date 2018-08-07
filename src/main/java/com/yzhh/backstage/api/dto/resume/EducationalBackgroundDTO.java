package com.yzhh.backstage.api.dto.resume;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class EducationalBackgroundDTO {

	private Long id;
	@NotNull
	@ApiModelProperty(value = "开始时间")
	private String startTime;
	@NotNull
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	@NotNull
	@ApiModelProperty(value = "学历")
	private String education;
	@NotNull
	@ApiModelProperty(value = "学校")
	private String school;
	@NotNull
	@ApiModelProperty(value = "城市")
	private String city;
	@NotNull
	@ApiModelProperty(value = "专业")
	private String major;
	@ApiModelProperty(value = "专业类别")
	private String majorType;
	@ApiModelProperty(value = "专业课程")
	private String majorCourses;
	@ApiModelProperty(value = "专业排名")
	private String ranking;
	@ApiModelProperty(value = "荣誉")
	private String honor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSchool() {
		return school;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getMajorCourses() {
		return majorCourses;
	}
	public void setMajorCourses(String majorCourses) {
		this.majorCourses = majorCourses;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
}
