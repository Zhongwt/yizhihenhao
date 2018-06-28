package com.yzhh.backstage.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.ILogDAO;
import com.yzhh.backstage.api.dao.mapper.custom.LogMapper2;
import com.yzhh.backstage.api.dto.admin.LogDTO;
import com.yzhh.backstage.api.entity.Log;
import com.yzhh.backstage.api.entity.LogExample;

@Repository("logDAO")
public class LogDAOImpl extends DAOImpl<Log, LogExample> implements ILogDAO {

	@Autowired
	private LogMapper2 mapper2;
	
	@Override
	public List<LogDTO> queryByPage(Map<String, Object> params) {
		return mapper2.queryByPage(params);
	}


}
