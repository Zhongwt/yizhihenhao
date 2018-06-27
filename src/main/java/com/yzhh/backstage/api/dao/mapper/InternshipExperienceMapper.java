package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.InternshipExperience;
import com.yzhh.backstage.api.entity.InternshipExperienceExample;
@Mapper
public interface InternshipExperienceMapper extends BaseMapper<InternshipExperience, InternshipExperienceExample>{
    
}