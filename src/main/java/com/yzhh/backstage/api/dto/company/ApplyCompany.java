package com.yzhh.backstage.api.dto.company;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ApplyCompany {

	@NotNull
	@ApiModelProperty(value = "公司名称")
	private String name;
	@NotNull
	@ApiModelProperty(value = "公司email")
	private String email;
	@NotNull
	@ApiModelProperty(value = "公司类型")
	private String companyType;
	@NotNull
	@ApiModelProperty(value = "公司注册号")
	private String registrationNumber;
	@NotNull
	@ApiModelProperty(value = "公司注册时间")
	private String establishTime;
	@NotNull
	@ApiModelProperty(value = "公司注册资本")
	private String registeredCapital;
	@NotNull
	@ApiModelProperty(value = "公司附件")
	private String attachment;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getEstablishTime() {
		return establishTime;
	}
	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}
	public String getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}
