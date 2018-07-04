package com.yzhh.backstage.api.dao;

import java.util.List;
import java.util.Map;

import com.yzhh.backstage.api.entity.AccountLog;
import com.yzhh.backstage.api.entity.AccountLogExample;

public interface IAccountLogDAO extends IDAO<AccountLog, AccountLogExample> {

	// 分页查询日志
	public List<AccountLog> queryByPage(Map<String, Object> params);

	// 计算公司数量
	public Long countByPage(Map<String, Object> params);

}
