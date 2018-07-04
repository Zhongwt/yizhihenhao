package com.yzhh.backstage.api.dto.account;

public class AccountDTO {

	private Long id;
	private double balance;			//总余额
	private double capital;			//本金
	private double largess;			//送的
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public double getLargess() {
		return largess;
	}
	public void setLargess(double largess) {
		this.largess = largess;
	}
}
