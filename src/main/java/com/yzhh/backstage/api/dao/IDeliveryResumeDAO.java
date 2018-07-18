package com.yzhh.backstage.api.dao;

import java.util.List;
import java.util.Map;

import com.yzhh.backstage.api.dto.jobseeker.DeliveryDTO;
import com.yzhh.backstage.api.entity.DeliveryResume;
import com.yzhh.backstage.api.entity.DeliveryResumeExample;

public interface IDeliveryResumeDAO extends IDAO<DeliveryResume, DeliveryResumeExample> {

	// 简历投递人数
	public long countDeliveryCount(Long positionId);

	// 获取投递支付记录
	public List<DeliveryDTO> queryByPage(Map<String, Object> params);
	// 获取投递支付记录
	public Long countByPage(Map<String, Object> params);
}
