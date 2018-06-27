package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.JobSeeker;
import com.yzhh.backstage.api.entity.JobSeekerExample;
@Mapper
public interface JobSeekerMapper extends BaseMapper<JobSeeker, JobSeekerExample>{
   
}