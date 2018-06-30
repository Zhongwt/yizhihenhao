package com.yzhh.backstage.api.dao;

import com.yzhh.backstage.api.entity.DeliveryResume;
import com.yzhh.backstage.api.entity.DeliveryResumeExample;

public interface IDeliveryResumeDAO extends IDAO<DeliveryResume, DeliveryResumeExample>{

	//简历投递人数
	public long countDeliveryCount(Long positionId);
}
