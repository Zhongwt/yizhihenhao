package com.yzhh.backstage.api.dto.account;

public class GiftDTO {
	private String label;
	private Long amount;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
}