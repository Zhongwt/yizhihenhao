package com.yzhh.backstage.api.dao.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.CompanyNotice;

@Mapper
public interface CompanyNoticeMapper2 {

	// 分页查询日志
	public List<CompanyNotice> queryByPage(Map<String, Object> params);

	// 计算公司数量
	public Long countByPage(Map<String, Object> params);
}