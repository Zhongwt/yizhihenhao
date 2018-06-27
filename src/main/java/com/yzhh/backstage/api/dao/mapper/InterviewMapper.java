package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Interview;
import com.yzhh.backstage.api.entity.InterviewExample;
@Mapper
public interface InterviewMapper extends BaseMapper<Interview, InterviewExample>{
   
}