package com.yzhh.backstage.api.dto.account;

public class AccountLogDTO {

	private Long id;
	private String updateTime;
	private double stream;		//流水
	private String note;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public double getStream() {
		return stream;
	}
	public void setStream(double stream) {
		this.stream = stream;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
