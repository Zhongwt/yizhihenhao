package com.yzhh.backstage.api.dao.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.dto.admin.LogDTO;

@Mapper
public interface LogMapper2 {

	// 分页查询日志
	public List<LogDTO> queryByPage(Map<String, Object> params);

	// 分页查询日志
	public Long countByPage(Map<String, Object> params);

}
