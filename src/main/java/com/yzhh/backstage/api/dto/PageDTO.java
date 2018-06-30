package com.yzhh.backstage.api.dto;

import java.util.List;

public class PageDTO<T> {

	private Long page;
	private Integer size;
	private List<T> list;
	private Long count;
	
	public PageDTO(){};
	public PageDTO(Long count,List<T> list ,Long page,Integer size){
		this.page = page;
		this.size = size;
		this.count = count;
		this.list = list;
	}
	
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
}
