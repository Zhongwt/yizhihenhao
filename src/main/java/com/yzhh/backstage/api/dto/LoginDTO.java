package com.yzhh.backstage.api.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class LoginDTO {

	@ApiModelProperty(value = "用户名")
	@NotNull
	private String username;	//
	@ApiModelProperty(value = "密码")
	@NotNull
	private String password;	//
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
