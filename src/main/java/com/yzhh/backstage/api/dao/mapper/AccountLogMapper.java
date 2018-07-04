package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.AccountLog;
import com.yzhh.backstage.api.entity.AccountLogExample;

@Mapper
public interface AccountLogMapper extends BaseMapper<AccountLog, AccountLogExample>{
    
}