package com.yzhh.backstage.api.service;

import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.jobseeker.JobSeekerDTO;
import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;
import com.yzhh.backstage.api.entity.JobSeeker;

public interface IJobSeekerService {

	//是否收藏职位
	public boolean isCollectionPosition(Long positionId,Long jobSeekerId);
	
	//收藏职位
	public void collectionPosition(Long positionId,Long jobSeekerId);
	
	//投递简历
	public void deliveryPosition(Long jobSeekerId,Long positionId,Long resumeId);
	
	//用户登录
	public UserDTO login(WeChatUserInfo user);
	
	//用户投递简历付款成功，记录账户流水
	//public void paySuccess(Long jobSeekerId,Long positionId,Integer totalFee);
	
	//通过jobSeekerId获取求职者信息
	public JobSeekerDTO findById(Long jobSeekerId);
	
	//通过jobSeekerId获取求职者信息
	public void editInfo(JobSeekerDTO jobSeekerDTO);
	
	//校验投递者信息
	public JobSeeker checkJobSeeker(Long jobSeekerId);
	
	//校验简历和职位匹配度
	public String matchResumeAndPosition(Long resumeId,Long positionId);
}
