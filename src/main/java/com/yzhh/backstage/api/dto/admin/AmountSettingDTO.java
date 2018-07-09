package com.yzhh.backstage.api.dto.admin;

import javax.validation.constraints.NotNull;

public class AmountSettingDTO {

	@NotNull
	private Long id;
	private String type;
	@NotNull
	private Double amount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
