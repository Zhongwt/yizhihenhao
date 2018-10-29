package com.yzhh.backstage.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.dao.IAccountDAO;
import com.yzhh.backstage.api.dao.IAccountLogDAO;
import com.yzhh.backstage.api.dao.IAmountSettingDAO;
import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.account.AccountDTO;
import com.yzhh.backstage.api.dto.account.AccountLogDTO;
import com.yzhh.backstage.api.dto.account.GiftDTO;
import com.yzhh.backstage.api.dto.jobseeker.DeliveryDTO;
import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.AccountExample;
import com.yzhh.backstage.api.entity.AccountLog;
import com.yzhh.backstage.api.entity.AmountSetting;
import com.yzhh.backstage.api.entity.AmountSettingExample;
import com.yzhh.backstage.api.enums.AccountSettingEnum;
import com.yzhh.backstage.api.enums.AccountTypeEnum;
import com.yzhh.backstage.api.service.IAccountService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;
import com.yzhh.backstage.api.util.DoubleFormat;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private IAccountDAO accountDAO;
	@Autowired
	private IAccountLogDAO accountLogDAO;
	@Autowired
	private IAmountSettingDAO amountSettingDAO;
	@Autowired
	private IDeliveryResumeDAO deliveryResumeDAO;
	
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
	public PageDTO<AccountLogDTO> getAccountLogList(Long accountId,String type, Long page, Integer size) {
		
		if(page == null) {
			page = 1L;
		}
		if(size == null) {
			size = 10;
		}
		List<AccountLogDTO> list = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		
		if(!StringUtils.isEmpty(type)) {
			if("支出".equals(type) || "收入".equals(type)) {
				params.put("type", type);
			}
		}
		
		params.put("accountId", accountId);
		params.put("starNum", (page - 1) * size);
		params.put("size", size);
		
		List<AccountLog> accountLogs = accountLogDAO.queryByPage(params);
		Long count = accountLogDAO.countByPage(params);
		
		if(CollectionUtils.isNotEmpty(accountLogs)) {
			for(AccountLog accountLog : accountLogs) {
				AccountLogDTO accountLogDTO = new AccountLogDTO();
				accountLogDTO.setId(accountLog.getId());
				accountLogDTO.setUpdateTime(DateUtils.longToString(accountLog.getLastAccess(), DateUtils.yymmddhhmmss));
				accountLogDTO.setStream(accountLog.getSteam());
				accountLogDTO.setNote(accountLog.getNote());
				list.add(accountLogDTO);
			}
		}
		
		return new PageDTO<>(count, list, page, size);
	}

	@Override
	public Double getAmountSettingByType(String type) {
		AmountSettingExample example = new AmountSettingExample();
		example.createCriteria().andTypeEqualTo(type);
		List<AmountSetting> list = amountSettingDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0).getAmount();
		}
		return 0D;
	}

	@Override
	public PageDTO<DeliveryDTO> deliveryPayList(Long jobSeekerId, Long page, Integer size) {
		
		if (page == null) {
			page = 1L;
		}
		if (size == null) {
			size = 10;
		}
		
		Map<String, Object> params = new HashedMap<>();
		params.put("jobSeekerId", jobSeekerId);
		params.put("starNum", (page - 1) * size);
		params.put("size", size);
		
		List<DeliveryDTO> list = deliveryResumeDAO.queryByPage(params);
		Long count = deliveryResumeDAO.countByPage(params);
		
		return new PageDTO<>(count, list, page, size);
	}

	@Override
	public void paySuccess(Long relationId, Integer type,Double totalFee) {
		Account account = accountDAO.getAccountByRelationId(relationId, type);
		
		if(account == null) {
			throw new BizException("用户账户未赵到，用户id"+relationId);
		}
		
		Double largessAmount = 0d;
		
		if(AccountTypeEnum.job_seeker.getId() == type) {
			if(totalFee >= 200) {
				largessAmount = this.getAmountSettingByType(AccountSettingEnum.job_seeker_sixth_gear.getName());
			}else if(totalFee >= 100){
				largessAmount = this.getAmountSettingByType(AccountSettingEnum.job_seeker_fifth_gear.getName());
			}else if(totalFee >= 50){
				largessAmount = this.getAmountSettingByType(AccountSettingEnum.job_seeker_fourth_gear.getName());
			}else if(totalFee >= 30){
				largessAmount = this.getAmountSettingByType(AccountSettingEnum.job_seeker_third_gear.getName());
			}else if(totalFee >= 20){
				largessAmount = this.getAmountSettingByType(AccountSettingEnum.job_seeker_second_gear.getName());
			}else if(totalFee >= 10){
				largessAmount = this.getAmountSettingByType(AccountSettingEnum.job_seeker_first_gear.getName());
			}
		}
		
		accountDAO.rechargeWater(account, totalFee,largessAmount , "充值【"+DoubleFormat.m2(totalFee)+"】元成功");
	}

	@Override
	public List<GiftDTO> getGift() {
		
		
		List<GiftDTO> giftList = new ArrayList<>();
		
		List<AmountSetting> list = amountSettingDAO.selectByExample(new AmountSettingExample());
		
		for(AmountSetting entity : list) {
			GiftDTO giftDTO = new GiftDTO();
			if(AccountSettingEnum.job_seeker_first_gear.getName().equals(entity.getType())) {
				giftDTO.setAmount(10L);
				giftDTO.setLabel("赠送"+entity.getAmount().intValue()+"元");
				giftList.add(giftDTO);
			}else if(AccountSettingEnum.job_seeker_second_gear.getName().equals(entity.getType())){
				giftDTO.setAmount(20L);
				giftDTO.setLabel("赠送"+entity.getAmount().intValue()+"元");
				giftList.add(giftDTO);
			}else if(AccountSettingEnum.job_seeker_third_gear.getName().equals(entity.getType())){
				giftDTO.setAmount(30L);
				giftDTO.setLabel("赠送"+entity.getAmount().intValue()+"元");
				giftList.add(giftDTO);
			}else if(AccountSettingEnum.job_seeker_fourth_gear.getName().equals(entity.getType())){
				giftDTO.setAmount(50L);
				giftDTO.setLabel("赠送"+entity.getAmount().intValue()+"元");
				giftList.add(giftDTO);
			}else if(AccountSettingEnum.job_seeker_fifth_gear.getName().equals(entity.getType())){
				giftDTO.setAmount(100L);
				giftDTO.setLabel("赠送"+entity.getAmount().intValue()+"元");
				giftList.add(giftDTO);
			}else if(AccountSettingEnum.job_seeker_sixth_gear.getName().equals(entity.getType())){
				giftDTO.setAmount(200L);
				giftDTO.setLabel("赠送"+entity.getAmount().intValue()+"元");
				giftList.add(giftDTO);
			}
		}
		
		return giftList;
	}
}
