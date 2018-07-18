package com.yzhh.backstage.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.dao.mapper.custom.DeliveryResumeMapper2;
import com.yzhh.backstage.api.dto.jobseeker.DeliveryDTO;
import com.yzhh.backstage.api.entity.DeliveryResume;
import com.yzhh.backstage.api.entity.DeliveryResumeExample;

@Repository("deliveryResumeDAO")
public class DeliveryResumeDAOImpl extends DAOImpl<DeliveryResume, DeliveryResumeExample> implements IDeliveryResumeDAO {

	@Autowired
	private DeliveryResumeMapper2 mapper2;
	
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

	@Override
	public List<DeliveryDTO> queryByPage(Map<String, Object> params) {
		return mapper2.queryByPage(params);
	}

	@Override
	public Long countByPage(Map<String, Object> params) {
		return mapper2.countByPage(params);
	}

}
