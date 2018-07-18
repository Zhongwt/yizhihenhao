package com.yzhh.backstage.api.dto.resume;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class InternshipExperienceDTO {

	private Long id;
	@NotNull
	@ApiModelProperty(value = "公司名字")
	private String companyName;
	@NotNull
	@ApiModelProperty(value = "城市")
	private String city;
	@NotNull
	@ApiModelProperty(value = "开始时间")
	private String startTime;
	@NotNull
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	@NotNull
	@ApiModelProperty(value = "实现职位")
	private String dutyName;
	@NotNull
	@ApiModelProperty(value = "实习描述")
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
