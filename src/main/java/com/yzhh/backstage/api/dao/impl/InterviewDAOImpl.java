package com.yzhh.backstage.api.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IInterviewDAO;
import com.yzhh.backstage.api.dao.mapper.custom.InterviewMapper2;
import com.yzhh.backstage.api.entity.Interview;
import com.yzhh.backstage.api.entity.InterviewExample;

@Repository("interviewDAO")
public class InterviewDAOImpl extends DAOImpl<Interview, InterviewExample> implements IInterviewDAO {

	@Autowired
	private InterviewMapper2 mapper2;
	
	@Override
	public Long countInterviewCount(Long companyId) {
		return mapper2.countInterview(companyId);
	}
}
