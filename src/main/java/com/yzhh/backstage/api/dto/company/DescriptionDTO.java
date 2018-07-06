package com.yzhh.backstage.api.dto.company;

import javax.validation.constraints.NotNull;

public class DescriptionDTO {
	
	@NotNull
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
