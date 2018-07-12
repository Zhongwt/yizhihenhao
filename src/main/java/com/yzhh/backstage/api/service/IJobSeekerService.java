package com.yzhh.backstage.api.service;

import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;

public interface IJobSeekerService {

	//是否收藏职位
	public boolean isCollectionPosition(Long positionId,Long jobSeekerId);
	
	//收藏职位
	public void collectionPosition(Long positionId,Long jobSeekerId);
	
	//投递简历
	public void deliveryPosition(Long jobSeekerId,Long positionId);
	
	//用户登录
	public UserDTO login(WeChatUserInfo user);
}
