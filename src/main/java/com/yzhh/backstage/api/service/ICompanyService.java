package com.yzhh.backstage.api.service;

import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.company.AddCompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanySearchDTO;

public interface ICompanyService {

	//查询企业列表
	public PageDTO<CompanyDTO> queryByPage(CompanySearchDTO companySearchDTO,Long page,Integer size);
	//保存企业
	public void addCompany(AddCompanyDTO addCompanyDTO);
	//查询企业
	public CompanyDTO findById(Long id);
	//更新企业基础信息
	public void update(CompanyDTO companyDTO);
	//审核通过企业
	public void passCompany(Long id);
	//移除企业
	public void removeCompany(Long id);
}
