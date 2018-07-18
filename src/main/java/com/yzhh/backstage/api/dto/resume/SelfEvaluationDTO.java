package com.yzhh.backstage.api.dto.resume;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class SelfEvaluationDTO {
	
	private Long id;
	@NotNull
	@ApiModelProperty(value = "自我评价描述")
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
