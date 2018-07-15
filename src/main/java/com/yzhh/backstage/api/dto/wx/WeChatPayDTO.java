package com.yzhh.backstage.api.dto.wx;

public class WeChatPayDTO {

	private String appId;
	private String timeStamp;
	private String nonceStr;
	private String prepayId;
	private String openId;
	private String paySign;
	
	public WeChatPayDTO() {}
	
	public WeChatPayDTO(String appId, String timeStamp, String nonceStr, String prepayId, String openId,
			String paySign) {
		this.appId = appId;
		this.timeStamp = timeStamp;
		this.nonceStr = nonceStr;
		this.prepayId = prepayId;
		this.openId = openId;
		this.paySign = paySign;
	}

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
}
