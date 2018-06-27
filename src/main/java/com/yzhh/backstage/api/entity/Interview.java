package com.yzhh.backstage.api.entity;

/**
 * @description:面试
 * @projectName:backstage-api
 * @className:Interview.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:12:20
 * @version 1.0.1
 */
public class Interview extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long deliveryResumeId;	//
    private Long interviewTime;			//
    private String address;					//
    private Integer status;					//
    private String contacts;				//
    private String phone;					//
    private String note;						//

    public Long getDeliveryResumeId() {
        return deliveryResumeId;
    }

    public void setDeliveryResumeId(Long deliveryResumeId) {
        this.deliveryResumeId = deliveryResumeId;
    }

    public Long getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Long interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}