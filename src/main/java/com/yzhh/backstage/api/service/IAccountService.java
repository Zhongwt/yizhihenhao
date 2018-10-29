package com.yzhh.backstage.api.service;

import java.util.List;

import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.account.AccountDTO;
import com.yzhh.backstage.api.dto.account.AccountLogDTO;
import com.yzhh.backstage.api.dto.account.GiftDTO;
import com.yzhh.backstage.api.dto.jobseeker.DeliveryDTO;

public interface IAccountService {

	//获取账户信息
	public AccountDTO getAccount(Long relationId,int type);
	
	//获取账户流水
	public PageDTO<AccountLogDTO> getAccountLogList(Long accountId,String type,Long page,Integer size);
	
	//获取金额配置
	public Double getAmountSettingByType(String type);
	
	//分页获取支付记录
	public PageDTO<DeliveryDTO> deliveryPayList(Long jobSeekerId,Long page,Integer size);
	
	//充值成功
	public void paySuccess(Long relationId,Integer type,Double totalFee);
	
	//获取充值赠送
	public List<GiftDTO> getGift(); 
}
