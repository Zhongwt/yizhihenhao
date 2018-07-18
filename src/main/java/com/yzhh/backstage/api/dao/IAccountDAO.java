package com.yzhh.backstage.api.dao;

import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.AccountExample;

public interface IAccountDAO extends IDAO<Account, AccountExample> {

	// 通过关联id获取账户
	public Account getAccountByRelationId(Long relationId, Integer type);

	// 消费流水
	public void consumptionWater(Account account, Double amount, String note);

	// 充值流水
	public void rechargeWater(Account account, Double capitalAmount,Double largessAmount, String note);

}
