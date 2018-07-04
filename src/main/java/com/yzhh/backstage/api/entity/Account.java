package com.yzhh.backstage.api.entity;

public class Account extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Integer type;
    private Long relationId;
    private Double balance;			//总余额
    private Double capital;				//本金
    private Double largess;			//赠品

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getLargess() {
        return largess;
    }

    public void setLargess(Double largess) {
        this.largess = largess;
    }
}