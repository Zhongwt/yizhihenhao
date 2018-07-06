package com.yzhh.backstage.api.dao.mapper.custom;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface InterviewMapper2{
   
	//统计公司面试数量
	public Long countInterview(@Param("companyId") Long companyId);
}