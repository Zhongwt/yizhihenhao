package com.yzhh.backstage.api.dto.wx;

import java.io.Serializable;

/**
 * 
 * @description:微信用户的基本信息
 * @author:
 * @createTime:2018年6月1日 下午3:37:59
 * @version 1.0
 */
public class WeChatUserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	// 用户的标识
	private String openId;
	private String nickName;
	private int gender;
	private String country;
	private String province;
	private String city;
	private String language;
	private String avatarUrl;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
