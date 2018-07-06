package com.yzhh.backstage.api.dto;

import javax.validation.constraints.NotNull;

public class UpdatePasswordDTO {
	@NotNull
	private String oldPassword;
	@NotNull
	private String newPassword;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
