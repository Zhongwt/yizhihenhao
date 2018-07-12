package com.yzhh.backstage.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IJobSeekerDAO;
import com.yzhh.backstage.api.entity.JobSeeker;
import com.yzhh.backstage.api.entity.JobSeekerExample;
import com.yzhh.backstage.api.util.CollectionUtils;

@Repository("jobSeekerDAO")
public class JobSeekerDAOImpl extends DAOImpl<JobSeeker, JobSeekerExample> implements IJobSeekerDAO {

	@Override
	public JobSeeker getJobSeekerByOpenId(String openId) {
		JobSeekerExample example = new JobSeekerExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		List<JobSeeker> list = mapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
