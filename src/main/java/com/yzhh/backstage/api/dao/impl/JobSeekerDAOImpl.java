package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IJobSeekerDAO;
import com.yzhh.backstage.api.entity.JobSeeker;
import com.yzhh.backstage.api.entity.JobSeekerExample;

@Repository("jobSeekerDAO")
public class JobSeekerDAOImpl extends DAOImpl<JobSeeker, JobSeekerExample> implements IJobSeekerDAO {

}
