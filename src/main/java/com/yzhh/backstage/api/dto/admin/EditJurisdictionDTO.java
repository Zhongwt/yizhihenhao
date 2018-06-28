package com.yzhh.backstage.api.dto.admin;

import javax.validation.constraints.NotNull;

public class EditJurisdictionDTO {
	
	@NotNull
	private Long id;
	
	private String jurisdiction;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
}
