package com.yzhh.backstage.api.dto.company;

public class CompanySearchDTO {

	private Integer status;
	private String searchKey;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
