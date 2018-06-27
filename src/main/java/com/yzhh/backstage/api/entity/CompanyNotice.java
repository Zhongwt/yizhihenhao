package com.yzhh.backstage.api.entity;

/**
 * @description:通知
 * @projectName:backstage-api
 * @className:CompanyNotice.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:09:25
 * @version 1.0.1
 */
public class CompanyNotice extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String message;			//
    private Long companyId;			//
    private Integer isRead;				//


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}