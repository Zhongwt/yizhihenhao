package com.yzhh.backstage.api.dto.company;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class StatementDTO {

	@NotNull
	@ApiModelProperty(value = "公司邮箱")
	private String email;
	@NotNull
	@ApiModelProperty(value = "申述理由")
	private String optionNote;
	@NotNull
	@ApiModelProperty(value = "申述附件")
	private String attachment;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOptionNote() {
		return optionNote;
	}
	public void setOptionNote(String optionNote) {
		this.optionNote = optionNote;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}
