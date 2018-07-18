package com.yzhh.backstage.api.dto.resume;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

//实习期望
public class InternshipExpectationDTO {

	@NotNull
	@ApiModelProperty(value = "期望职位")
	private String wishPositionName;
	@NotNull
	@ApiModelProperty(value = "期望城市")
	private String wishCity;
	@NotNull
	@ApiModelProperty(value = "期望周工作天数")
	private String workDay;
	@NotNull
	@ApiModelProperty(value = "实习时间")
	private String internshipTime;
	@NotNull
	@ApiModelProperty(value = "日薪")
	private String perDiem;
	@NotNull
	@ApiModelProperty(value = "到岗日期")
	private String arrivalDay;
	
	public String getWishPositionName() {
		return wishPositionName;
	}
	public void setWishPositionName(String wishPositionName) {
		this.wishPositionName = wishPositionName;
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
	public String getInternshipTime() {
		return internshipTime;
	}
	public void setInternshipTime(String internshipTime) {
		this.internshipTime = internshipTime;
	}
	public String getPerDiem() {
		return perDiem;
	}
	public void setPerDiem(String perDiem) {
		this.perDiem = perDiem;
	}
	public String getArrivalDay() {
		return arrivalDay;
	}
	public void setArrivalDay(String arrivalDay) {
		this.arrivalDay = arrivalDay;
	}
}
