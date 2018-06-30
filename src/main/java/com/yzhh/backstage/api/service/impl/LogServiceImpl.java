package com.yzhh.backstage.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzhh.backstage.api.dao.ILogDAO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.admin.LogDTO;
import com.yzhh.backstage.api.entity.Log;
import com.yzhh.backstage.api.entity.LogExample;
import com.yzhh.backstage.api.service.ILogService;

@Service
public class LogServiceImpl implements ILogService{

	@Autowired
	private ILogDAO logDAO;
	
	@Override
	public void addLog(Long operatorId,String operator,String ip,String note) {
		Log log = new Log();
		Long date = new Date().getTime();
		log.setLastAccess(date);
		log.setOperatorId(operatorId);
		log.setOperator(operator);
		log.setOperateTime(date);
		log.setIp(ip);
		log.setNote(note);
		logDAO.insertSelective(log);
	}

	@Override
	public PageDTO<LogDTO> queryPage(Long operatorId,Long page, Integer size) {
		if(page == null) {
			page = 1l;
		}
		if(size == null) {
			size = 10;
		}
		Map<String,Object> params = new HashMap<>();
		params.put("operatorId", operatorId);
		
		List<LogDTO> list = logDAO.queryByPage(params);
		Long count = logDAO.countByPage(params);
		
		return new PageDTO<LogDTO>(count,list,page,size);
	}

	@Override
	public void emptyLog(Long operatorId) {
		LogExample example = new LogExample();
		example.createCriteria().andOperatorIdEqualTo(operatorId);
		logDAO.deleteByExample(example);
	}

}
