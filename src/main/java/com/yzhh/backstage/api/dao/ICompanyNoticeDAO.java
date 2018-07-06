package com.yzhh.backstage.api.dao;

import java.util.List;
import java.util.Map;

import com.yzhh.backstage.api.entity.CompanyNotice;
import com.yzhh.backstage.api.entity.CompanyNoticeExample;

public interface ICompanyNoticeDAO extends IDAO<CompanyNotice, CompanyNoticeExample> {

	// 分页查询日志
	public List<CompanyNotice> queryByPage(Map<String, Object> params);

	// 计算公司数量
	public Long countByPage(Map<String, Object> params);
}
