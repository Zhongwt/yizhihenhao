package com.yzhh.backstage.api.dto.resume;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class SkillHobbyDTO {
	
	private Long id;
	@NotNull
	@ApiModelProperty(value = "特殊爱好或能力")
	private String name;
	@NotNull
	@ApiModelProperty(value = "技能熟练度")
	private String level;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
