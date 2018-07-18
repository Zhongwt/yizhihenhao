package com.yzhh.backstage.api.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IAccountDAO;
import com.yzhh.backstage.api.dao.mapper.AccountLogMapper;
import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.AccountExample;
import com.yzhh.backstage.api.entity.AccountLog;
import com.yzhh.backstage.api.util.CollectionUtils;

@Repository("accountDAO")
public class AccountDAOImpl extends DAOImpl<Account, AccountExample> implements IAccountDAO {

	@Autowired
	private AccountLogMapper accountLogMappper;
	
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

	@Override
	public void consumptionWater(Account account, Double amount, String note) {
		Long lastAccess = new Date().getTime();
		AccountLog log = new AccountLog();
		log.setLastAccess(lastAccess);
		log.setAccountId(account.getId());
		log.setSteam(-amount);
		log.setNote(note);
		accountLogMappper.insertSelective(log);
		
		double balance =  account.getBalance();
		//本金
		double capital = account.getCapital();
		//赠品
		double largess = account.getLargess();
		
		if(capital - amount < 0) {
			capital = 0d;
			largess = largess + (capital - amount);
		}else {
			capital = capital - amount;
		}
		balance = balance - amount;
		
		Account newAccount = new Account();
		newAccount.setId(account.getId());
		newAccount.setLastAccess(account.getLastAccess());
		newAccount.setBalance(balance);
		newAccount.setCapital(capital);
		newAccount.setLargess(largess);
		mapper.updateByPrimaryKeySelective(newAccount);
	}

	@Override
	public void rechargeWater(Account account, Double capitalAmount, Double largessAmount, String note) {
		Long lastAccess = new Date().getTime();
		AccountLog log = new AccountLog();
		log.setLastAccess(lastAccess);
		log.setAccountId(account.getId());
		log.setSteam(capitalAmount);
		log.setNote(note);
		accountLogMappper.insertSelective(log);
		
		log.setId(null);
		log.setLastAccess(lastAccess);
		log.setAccountId(account.getId());
		log.setSteam(largessAmount);
		log.setNote("赠送");
		accountLogMappper.insertSelective(log);
		
		Account newAccount = new Account();
		newAccount.setId(account.getId());
		newAccount.setLastAccess(account.getLastAccess());
		newAccount.setBalance(newAccount.getBalance().doubleValue() + capitalAmount.doubleValue() + largessAmount.doubleValue());
		newAccount.setCapital(newAccount.getCapital().doubleValue() + capitalAmount.doubleValue());
		newAccount.setLargess(newAccount.getLargess().doubleValue() + largessAmount.doubleValue());
		mapper.updateByPrimaryKeySelective(newAccount);
	}
}



















