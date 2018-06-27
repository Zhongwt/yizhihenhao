package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Admin;
import com.yzhh.backstage.api.entity.AdminExample;

@Mapper
public interface AdminMapper extends BaseMapper<Admin, AdminExample>{
    
}