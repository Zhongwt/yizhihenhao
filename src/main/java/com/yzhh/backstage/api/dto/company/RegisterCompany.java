package com.yzhh.backstage.api.dto.company;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class RegisterCompany {
	
	@NotNull
	@ApiModelProperty(value = "公司注册手机号")
	private String phone;
	@NotNull
	@ApiModelProperty(value = "公司注册密码")
	private String password;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
