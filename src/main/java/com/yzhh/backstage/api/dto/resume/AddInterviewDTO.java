package com.yzhh.backstage.api.dto.resume;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AddInterviewDTO {

	@NotNull
	@ApiModelProperty(value = "简历投递id")
	private Long deliveryResumeId;
	@NotNull
	@ApiModelProperty(value = "面试时间")
	private String interviewTime;
	@NotNull
	@ApiModelProperty(value = "面试地址")
	private String address;
	@NotNull
	@ApiModelProperty(value = "联系人电话")
	private String phone;
	@NotNull
	@ApiModelProperty(value = "联系人")
	private String contacts;
	@NotNull
	@ApiModelProperty(value = "主题")
	private String subject;
	
	@ApiModelProperty(value = "备注")
	private String note;
	
	public Long getDeliveryResumeId() {
		return deliveryResumeId;
	}
	public void setDeliveryResumeId(Long deliveryResumeId) {
		this.deliveryResumeId = deliveryResumeId;
	}
	public String getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
