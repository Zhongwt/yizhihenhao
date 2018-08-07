package com.yzhh.backstage.api.dao.mapper.custom;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface InterviewMapper2{
   
	//统计公司面试数量
	public Long countInterview(Map<String,Object> params);
}