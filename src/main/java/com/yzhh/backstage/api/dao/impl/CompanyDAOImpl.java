package com.yzhh.backstage.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.ICompanyDAO;
import com.yzhh.backstage.api.dao.mapper.custom.CompanyMapper2;
import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.entity.CompanyExample;

@Repository("companyDAO")
public class CompanyDAOImpl extends DAOImpl<Company, CompanyExample> implements ICompanyDAO {

	@Autowired
	private CompanyMapper2 mapper2;
	
	@Override
	public List<Company> queryByPage(Map<String, Object> params) {
		return mapper2.queryByPage(params);
	}

	@Override
	public Long countByPage(Map<String, Object> params) {
		return mapper2.countByPage(params);
	}


}
