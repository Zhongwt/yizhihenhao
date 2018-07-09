package com.yzhh.backstage.api.dto;

import javax.validation.constraints.NotNull;

public class ForgetPasswordDTO {

	@NotNull
	private String phone;
	@NotNull
	private String code;
	@NotNull
	private String newPassword;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
