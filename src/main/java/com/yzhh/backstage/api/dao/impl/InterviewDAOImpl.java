package com.yzhh.backstage.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IInterviewDAO;
import com.yzhh.backstage.api.dao.mapper.custom.InterviewMapper2;
import com.yzhh.backstage.api.entity.Interview;
import com.yzhh.backstage.api.entity.InterviewExample;
import com.yzhh.backstage.api.util.CollectionUtils;

@Repository("interviewDAO")
public class InterviewDAOImpl extends DAOImpl<Interview, InterviewExample> implements IInterviewDAO {

	@Autowired
	private InterviewMapper2 mapper2;
	
	@Override
	public Long countInterviewCount(Map<String,Object> params) {
		return mapper2.countInterview(params);
	}

	@Override
	public Interview getInterviewByDeliveryResumeId(Long deliveryResumeId) {
		InterviewExample example = new InterviewExample();
		example.createCriteria().andDeliveryResumeIdEqualTo(deliveryResumeId);
		List<Interview> list = mapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}
