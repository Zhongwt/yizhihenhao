package com.yzhh.backstage.api.dao;

import java.util.List;
import java.util.Map;

import com.yzhh.backstage.api.dto.admin.LogDTO;
import com.yzhh.backstage.api.entity.Log;
import com.yzhh.backstage.api.entity.LogExample;

public interface ILogDAO extends IDAO<Log, LogExample>{

	//分页查询日志
	public List<LogDTO> queryByPage(Map<String,Object> params);
	
}
