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
	private String eduation;
	@NotNull
	@ApiModelProperty(value = "学校")
	private String school;
	@NotNull
	@ApiModelProperty(value = "城市")
	private String city;
	@NotNull
	@ApiModelProperty(value = "专业")
	private String major;
	
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
	public String getEduation() {
		return eduation;
	}
	public void setEduation(String eduation) {
		this.eduation = eduation;
	}
	public String getSchool() {
		return school;
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
}
