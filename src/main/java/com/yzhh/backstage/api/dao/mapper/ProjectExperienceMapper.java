package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.ProjectExperience;
import com.yzhh.backstage.api.entity.ProjectExperienceExample;
@Mapper
public interface ProjectExperienceMapper extends BaseMapper<ProjectExperience, ProjectExperienceExample>{
    
}