package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.SkillHobby;
import com.yzhh.backstage.api.entity.SkillHobbyExample;

@Mapper
public interface SkillHobbyMapper extends BaseMapper< SkillHobby, SkillHobbyExample>{
    
}