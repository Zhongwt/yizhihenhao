package com.yzhh.backstage.api.dto.jobseeker;

import io.swagger.annotations.ApiModelProperty;

public class FeedBackDTO {

	@ApiModelProperty(value = "练习方式")
	private String contactWay;
	@ApiModelProperty(value = "反馈意见")
	private String feedback;
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
