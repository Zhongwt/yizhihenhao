package com.yzhh.backstage.api.dto.jobseeker;

import io.swagger.annotations.ApiModelProperty;

//投递记录
public class DeliveryDTO {

	private Long id;
	@ApiModelProperty(value = "公司名称")
	private String companyName;
	@ApiModelProperty(value = "公司logo")
	private String companyLogo;
	@ApiModelProperty(value = "职位名称")
	private String positionName;
	@ApiModelProperty(value = "城市")
	private String city;
	@ApiModelProperty(value = "周工作天数")
	private String workDay;
	@ApiModelProperty(value = "投递时间")
	private String deliveryTime;
	@ApiModelProperty(value = "付费")
	private Double money;
	
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
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
}
