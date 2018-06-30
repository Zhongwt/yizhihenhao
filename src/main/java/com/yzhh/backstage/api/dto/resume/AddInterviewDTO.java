package com.yzhh.backstage.api.dto.resume;

import javax.validation.constraints.NotNull;

public class AddInterviewDTO {

	@NotNull
	private Long deliveryResumeId;
	@NotNull
	private String interviewTime;
	@NotNull
	private String address;
	@NotNull
	private String phone;
	@NotNull
	private String contacts;
	
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
}
