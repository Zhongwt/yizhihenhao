package com.yzhh.backstage.api.dto.resume;

import io.swagger.annotations.ApiModelProperty;

public class ResumePoorDTO {

	private Long id;
	@ApiModelProperty(value = "头像")
	private String picUrl;
	@ApiModelProperty(value = "是否默认")
	private Integer isDefault;
	@ApiModelProperty(value = "简历名称")
	private String name;
	@ApiModelProperty(value = "求职者id")
	private String jobSeekerName;
	@ApiModelProperty(value = "完整度")
	private String integrity;
	@ApiModelProperty(value = "期望城市")
	private String wishCity;
	@ApiModelProperty(value = "工作天数")
	private String workDay;
	@ApiModelProperty(value = "期望职位名称")
	private String wishPositionName;
	@ApiModelProperty(value = "更新时间")
	private String updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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
	public String getIntegrity() {
		return integrity;
	}
	public void setIntegrity(String integrity) {
		this.integrity = integrity;
	}
	public String getWishCity() {
		return wishCity;
	}
	public void setWishCity(String wishCity) {
		this.wishCity = wishCity;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getWishPositionName() {
		return wishPositionName;
	}
	public void setWishPositionName(String wishPositionName) {
		this.wishPositionName = wishPositionName;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
