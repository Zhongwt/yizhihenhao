package com.yzhh.backstage.api.entity;

public class AmountSetting extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String type;
    private Double amount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}