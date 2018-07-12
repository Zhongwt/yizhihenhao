package com.yzhh.backstage.api.dao;

import com.yzhh.backstage.api.entity.JobSeeker;
import com.yzhh.backstage.api.entity.JobSeekerExample;

public interface IJobSeekerDAO extends IDAO<JobSeeker, JobSeekerExample> {

	//通过openid获取用户
	public JobSeeker getJobSeekerByOpenId(String openId);	
}
