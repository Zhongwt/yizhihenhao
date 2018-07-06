package com.yzhh.backstage.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.ICompanyNoticeDAO;
import com.yzhh.backstage.api.dao.mapper.custom.CompanyNoticeMapper2;
import com.yzhh.backstage.api.entity.CompanyNotice;
import com.yzhh.backstage.api.entity.CompanyNoticeExample;

@Repository("companyNoticeDAO")
public class CompanyNoticeDAOImpl extends DAOImpl<CompanyNotice, CompanyNoticeExample> implements ICompanyNoticeDAO {

	@Autowired
	private CompanyNoticeMapper2 mapper2;
	
	@Override
	public List<CompanyNotice> queryByPage(Map<String, Object> params) {
		return mapper2.queryByPage(params);
	}

	@Override
	public Long countByPage(Map<String, Object> params) {
		return mapper2.countByPage(params);
	}


}
