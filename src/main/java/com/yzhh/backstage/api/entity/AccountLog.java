package com.yzhh.backstage.api.entity;

public class AccountLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Long accountId;
    private Double steam;
    private String note;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getSteam() {
        return steam;
    }

    public void setSteam(Double steam) {
        this.steam = steam;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}