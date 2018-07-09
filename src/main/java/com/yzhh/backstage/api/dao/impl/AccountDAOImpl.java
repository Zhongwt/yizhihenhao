package com.yzhh.backstage.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IAccountDAO;
import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.AccountExample;
import com.yzhh.backstage.api.util.CollectionUtils;

@Repository("accountDAO")
public class AccountDAOImpl extends DAOImpl<Account, AccountExample> implements IAccountDAO {

	@Override
	public Account getAccountByRelationId(Long relationId,Integer type) {
		
		AccountExample example = new AccountExample();
		example.createCriteria().andRelationIdEqualTo(relationId).andTypeEqualTo(type);
		List<Account> accountList = mapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(accountList)) {
			return accountList.get(0);
		}
		return null;
	}


}
