package com.yzhh.backstage.api.service;

import com.yzhh.backstage.api.dto.wx.WeChatAppLoginReq;
import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;

public interface IWxService {

	public WeChatUserInfo getUserInfoService(WeChatAppLoginReq req,String openId,String sessionKey);
}
