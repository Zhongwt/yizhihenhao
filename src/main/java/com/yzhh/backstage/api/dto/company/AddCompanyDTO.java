package com.yzhh.backstage.api.dto.company;

import javax.validation.constraints.NotNull;

public class AddCompanyDTO {

	@NotNull
	private String phone;
	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private String code;
	@NotNull
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
