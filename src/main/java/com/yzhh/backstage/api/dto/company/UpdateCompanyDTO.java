package com.yzhh.backstage.api.dto.company;

import javax.validation.constraints.NotNull;

public class UpdateCompanyDTO {

	@NotNull
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String note;
	@NotNull
	private String website;
	@NotNull
	private String field;
	@NotNull
	private String scale;
	@NotNull
	private String city;
	
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
