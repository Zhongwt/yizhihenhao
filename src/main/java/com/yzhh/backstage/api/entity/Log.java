package com.yzhh.backstage.api.entity;

/**
 * @description:管理员日志
 * @projectName:backstage-api
 * @className:Log.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:14:58
 * @version 1.0.1
 */
public class Log extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String operator;			//
    private Long operateTime;		//
    private String ip;						//
    private String note;					//

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}