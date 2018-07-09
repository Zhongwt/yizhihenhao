package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IAmountSettingDAO;
import com.yzhh.backstage.api.entity.AmountSetting;
import com.yzhh.backstage.api.entity.AmountSettingExample;

@Repository("amountSettingDAO")
public class AmountSettingDAOImpl extends DAOImpl<AmountSetting, AmountSettingExample> implements IAmountSettingDAO{

}
