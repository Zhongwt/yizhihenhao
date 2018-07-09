package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.CompanyJobSeeker;
import com.yzhh.backstage.api.entity.CompanyJobSeekerExample;
@Mapper
public interface CompanyJobSeekerMapper extends BaseMapper<CompanyJobSeeker, CompanyJobSeekerExample>{
}