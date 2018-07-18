package com.yzhh.backstage.api.dao.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Position;

@Mapper
public interface PositionMapper2 {

	// 分页查询日志
	public List<Position> queryByPage(Map<String, Object> params);

	// 计算公司数量
	public Long countByPage(Map<String, Object> params);
	
	//通过职位获取城市
	public List<String> getCityForPosition();
}