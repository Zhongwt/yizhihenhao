package com.yzhh.backstage.api.dto.wx;

import io.swagger.annotations.ApiModelProperty;

public class WeChatAppLoginReq {

	@ApiModelProperty(value = "登录code，由wx.login获得")
	private String code;						//
	@ApiModelProperty(value = "登录rawData，由wx.wx.getUserInfo()获得")
	private String rawData;				//
	@ApiModelProperty(value = "登录encryptedData，由wx.wx.getUserInfo()获得")
	private String encryptedData;		//
	@ApiModelProperty(value = "登录iv，由wx.wx.getUserInfo()获得")
	private String iv;							//
	@ApiModelProperty(value = "登录签名signature，由wx.wx.getUserInfo()获得")
	private String signature;				//
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRawData() {
		return rawData;
	}
	public void setRawData(String rawData) {
		this.rawData = rawData;
	}
	public String getEncryptedData() {
		return encryptedData;
	}
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	public String getIv() {
		return iv;
	}
	public void setIv(String iv) {
		this.iv = iv;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
