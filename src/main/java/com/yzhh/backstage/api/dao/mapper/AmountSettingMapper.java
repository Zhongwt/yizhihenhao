package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.AmountSetting;
import com.yzhh.backstage.api.entity.AmountSettingExample;

@Mapper
public interface AmountSettingMapper extends BaseMapper<AmountSetting, AmountSettingExample>{
}