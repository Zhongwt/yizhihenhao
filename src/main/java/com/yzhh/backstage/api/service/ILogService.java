package com.yzhh.backstage.api.service;

import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.admin.LogDTO;

public interface ILogService {

	public void addLog(Long operatorId,String operator,String ip,String note);
	
	public PageDTO<LogDTO> queryPage(Long operatorId,Long page,Integer size);
	
	public void emptyLog(Long operatorId);
}
