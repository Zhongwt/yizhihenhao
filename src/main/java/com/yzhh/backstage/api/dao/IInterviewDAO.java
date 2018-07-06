package com.yzhh.backstage.api.dao;

import com.yzhh.backstage.api.entity.Interview;
import com.yzhh.backstage.api.entity.InterviewExample;

public interface IInterviewDAO extends IDAO<Interview, InterviewExample> {

	//某个公司的面试数量
	public Long countInterviewCount(Long companyId);
}
