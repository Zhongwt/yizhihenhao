package com.yzhh.backstage.api.dto.company;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class UpdateCompanyDTO {

	@NotNull
	@ApiModelProperty(value = "公司logo")
	private String logo;
	@NotNull
	@ApiModelProperty(value = "公司昵称")
	private String nickName;;
	@NotNull
	@ApiModelProperty(value = "公司简介")
	private String note;
	@NotNull
	@ApiModelProperty(value = "网站")
	private String website;
	@NotNull
	@ApiModelProperty(value = "领域")
	private String field;
	@NotNull
	@ApiModelProperty(value = "规模")
	private String scale;
	@NotNull
	@ApiModelProperty(value = "公司市")
	private String city;
	@NotNull
	@ApiModelProperty(value = "公司省")
	private String province;
	@NotNull
	@ApiModelProperty(value = "公司县")
	private String area;
	@NotNull
	@ApiModelProperty(value = "公司描述")
	private String description;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
}
