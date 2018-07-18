package com.yzhh.backstage.api.dto.resume;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ProjectExperienceDTO {

	private Long id;
	@NotNull
	@ApiModelProperty(value = "开始时间")
	private String startTime;
	@NotNull
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	@NotNull
	@ApiModelProperty(value = "城市")
	private String city;
	@NotNull
	@ApiModelProperty(value = "项目名称")
	private String projectName;
	@NotNull
	@ApiModelProperty(value = "项目角色")
	private String role;
	@NotNull
	@ApiModelProperty(value = "项目描述")
	private String description;
	
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
