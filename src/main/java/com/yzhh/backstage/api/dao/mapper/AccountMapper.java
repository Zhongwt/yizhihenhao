package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.AccountExample;

@Mapper
public interface AccountMapper extends BaseMapper<Account, AccountExample> {
}