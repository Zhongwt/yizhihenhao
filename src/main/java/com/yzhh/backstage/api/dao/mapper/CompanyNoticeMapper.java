package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.CompanyNotice;
import com.yzhh.backstage.api.entity.CompanyNoticeExample;
@Mapper
public interface CompanyNoticeMapper extends BaseMapper<CompanyNotice, CompanyNoticeExample>{
    
}