package com.yzhh.backstage.api.service;

import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;

public interface ICompanyService {

	//查询企业列表
	public PageDTO<CompanyDTO> queryByPage(String name,Integer status,Long page,Integer size);
	
}
