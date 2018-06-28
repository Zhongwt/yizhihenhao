package com.yzhh.backstage.api.dao;

import java.util.List;
import java.util.Map;

import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.entity.CompanyExample;

public interface ICompanyDAO extends IDAO<Company, CompanyExample> {

	// 分页查询日志
	public List<Company> queryByPage(Map<String, Object> params);
	//计算公司数量
	public Long countByPage(Map<String,Object> params);
}
