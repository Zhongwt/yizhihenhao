package com.yzhh.backstage.api.service;

import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.account.AccountDTO;
import com.yzhh.backstage.api.dto.account.AccountLogDTO;

public interface IAccountService {

	//获取账户信息
	public AccountDTO getAccount(Long relationId,int type);
	
	//获取账户流水
	public PageDTO<AccountLogDTO> getAccountLogList(Long accountId,Long page,Integer size);
}
