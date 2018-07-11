package com.yzhh.backstage.api.service;

public interface IJobSeekerService {

	//是否收藏职位
	public boolean isCollectionPosition(Long positionId,Long jobSeekerId);
	
	//收藏职位
	public void collectionPosition(Long positionId,Long jobSeekerId);
	
	//投递简历
	public void deliveryPosition(Long jobSeekerId,Long positionId);
}
