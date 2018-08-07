package com.yzhh.backstage.api.dao;

import java.util.Map;

import com.yzhh.backstage.api.entity.Interview;
import com.yzhh.backstage.api.entity.InterviewExample;

public interface IInterviewDAO extends IDAO<Interview, InterviewExample> {

	//某个公司的面试数量
	public Long countInterviewCount(Map<String,Object> params);
	//获取某个投递的面试信息
	public Interview getInterviewByDeliveryResumeId(Long deliveryResumeId);
}
