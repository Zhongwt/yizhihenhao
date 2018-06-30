package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.entity.DeliveryResume;
import com.yzhh.backstage.api.entity.DeliveryResumeExample;

@Repository("deliveryResumeDAO")
public class DeliveryResumeDAOImpl extends DAOImpl<DeliveryResume, DeliveryResumeExample> implements IDeliveryResumeDAO {

	@Override
	public long countDeliveryCount(Long positionId) {
		
		DeliveryResumeExample example = new DeliveryResumeExample();
		example.createCriteria().andPositionIdEqualTo(positionId);
		Long count = mapper.countByExample(example);
		if(count == null) {
			count = 0L;
		}
		
		return count;
	}

}
