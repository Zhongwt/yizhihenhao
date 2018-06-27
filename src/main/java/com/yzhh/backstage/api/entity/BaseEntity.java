package com.yzhh.backstage.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * description 基础类
 * Date:     2018年6月12日 下午4:10:12 <br/>
 * @author   wentao
 * @version
 * @see
 */
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1546301367884130618L;

	private Integer id;
	private Date gmtCreate;
	private Date gmtModify;
	private Integer isDelete;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModify() {
		return gmtModify;
	}
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}

