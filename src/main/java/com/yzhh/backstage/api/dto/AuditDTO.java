package com.yzhh.backstage.api.dto;

import javax.validation.constraints.NotNull;

public class AuditDTO {

	@NotNull
	private Long id;
	@NotNull
	private String status;
	@NotNull
	private String note;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
