package com.yzhh.backstage.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzhh.backstage.api.dao.IAccountDAO;
import com.yzhh.backstage.api.dao.IAccountLogDAO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.account.AccountDTO;
import com.yzhh.backstage.api.dto.account.AccountLogDTO;
import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.AccountExample;
import com.yzhh.backstage.api.entity.AccountLog;
import com.yzhh.backstage.api.service.IAccountService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private IAccountDAO accountDAO;
	@Autowired
	private IAccountLogDAO accountLogDAO;
	
	@Override
	public AccountDTO getAccount(Long relationId, int type) {
		
		AccountExample example = new AccountExample();
		example.createCriteria().andTypeEqualTo(type).andRelationIdEqualTo(relationId);
		
		List<Account> list = accountDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			Account account = list.get(0);
			
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setId(account.getId());
			accountDTO.setBalance(account.getBalance());
			accountDTO.setCapital(account.getCapital());
			accountDTO.setLargess(account.getLargess());
			
			return accountDTO;
			
		}
		
		return null;
	}

	@Override
	public PageDTO<AccountLogDTO> getAccountLogList(Long accountId, Long page, Integer size) {
		
		if(page == null) {
			page = 1L;
		}
		if(size == null) {
			size = 10;
		}
		List<AccountLogDTO> list = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		
		params.put("accountId", accountId);
		params.put("starNum", (page - 1) * size);
		params.put("size", size);
		
		List<AccountLog> accountLogs = accountLogDAO.queryByPage(params);
		Long count = accountLogDAO.countByPage(params);
		
		if(CollectionUtils.isNotEmpty(accountLogs)) {
			for(AccountLog accountLog : accountLogs) {
				AccountLogDTO accountLogDTO = new AccountLogDTO();
				accountLogDTO.setId(accountLog.getId());
				accountLogDTO.setUpdateTime(DateUtils.longToString(accountLog.getLastAccess(), null));
				accountLogDTO.setStream(accountLog.getSteam());
				accountLogDTO.setNote(accountLog.getNote());
				list.add(accountLogDTO);
			}
		}
		
		return new PageDTO<>(count, list, page, size);
	}

}
