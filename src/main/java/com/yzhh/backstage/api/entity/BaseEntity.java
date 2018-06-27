package com.yzhh.backstage.api.entity;

import java.io.Serializable;

/**
 * description 基础类
 * Date:     2018年6月12日 下午4:10:12 <br/>
 * @author   wentao
 * @version
 * @see
 */
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1546301367884130618L;

	private Long id;
	private Long  lastAccess;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLastAccess() {
		return lastAccess;
	}
	public void setLastAccess(Long lastAccess) {
		this.lastAccess = lastAccess;
	}
}

