package com.yzhh.backstage.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IAccountLogDAO;
import com.yzhh.backstage.api.dao.mapper.custom.AccountLogMapper2;
import com.yzhh.backstage.api.entity.AccountLog;
import com.yzhh.backstage.api.entity.AccountLogExample;

@Repository("accountLogDAO")
public class AccountLogDAOImpl extends DAOImpl<AccountLog, AccountLogExample> implements IAccountLogDAO {

	@Autowired
	private AccountLogMapper2 mapper2;
	
	@Override
	public List<AccountLog> queryByPage(Map<String, Object> params) {
		return mapper2.queryByPage(params);
	}

	@Override
	public Long countByPage(Map<String, Object> params) {
		return mapper2.countByPage(params);
	}


}
