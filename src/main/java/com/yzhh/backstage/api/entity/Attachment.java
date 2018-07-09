package com.yzhh.backstage.api.entity;

public class Attachment extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}