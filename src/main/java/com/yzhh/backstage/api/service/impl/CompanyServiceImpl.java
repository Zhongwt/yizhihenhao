package com.yzhh.backstage.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzhh.backstage.api.dao.ICompanyDAO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.enums.CompanyStatusEnum;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDAO companyDAO;

	@Override
	public PageDTO<CompanyDTO> queryByPage(String name, Integer status, Long page, Integer size) {

		if (page == null) {
			page = 1L;
		}
		if (size == null) {
			size = 10;
		}
		List<CompanyDTO> list = new ArrayList<>();

		Map<String, Object> params = new HashedMap<>();
		if (!StringUtils.isEmpty(name)) {
			params.put("searchKey", name);
		}
		if (status != null) {
			params.put("status", status);
		}
		params.put("starNum", (page - 1) * size);
		params.put("size", size);

		List<Company> companyList = companyDAO.queryByPage(params);
		Long count = companyDAO.countByPage(params);

		if (CollectionUtils.isNotEmpty(companyList)) {
			for (Company company : companyList) {
				CompanyDTO companyDTO = new CompanyDTO();
				companyDTO.setId(company.getId());
				companyDTO.setCity(company.getCity());
				companyDTO.setName(company.getName());
				companyDTO.setJoinDate(DateUtils.longToString(company.getJoinDate(), null));
				companyDTO.setStatus(CompanyStatusEnum.getValueById(company.getStatus()));
				list.add(companyDTO);
			}
		}

		PageDTO<CompanyDTO> p = new PageDTO<>();
		p.setCount(count);
		p.setList(list);
		p.setPage(page);
		p.setSize(size);

		return p;
	}

}
