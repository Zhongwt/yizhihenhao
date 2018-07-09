package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.entity.CompanyExample;
@Mapper
public interface CompanyMapper extends BaseMapper<Company, CompanyExample>{
}