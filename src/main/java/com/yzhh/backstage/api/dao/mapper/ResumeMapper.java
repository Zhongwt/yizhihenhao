package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Resume;
import com.yzhh.backstage.api.entity.ResumeExample;
@Mapper
public interface ResumeMapper extends BaseMapper<Resume, ResumeExample>{
    
}