package com.yzhh.backstage.api.service;

import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;

public interface IWxService {

	public WeChatUserInfo getUserInfoService(String token, String openId);
}
