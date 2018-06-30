package com.yzhh.backstage.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.dao.ICompanyDAO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.company.AddCompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanySearchDTO;
import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.entity.CompanyExample;
import com.yzhh.backstage.api.enums.CompanyStatusEnum;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDAO companyDAO;

	@Override
	public PageDTO<CompanyDTO> queryByPage(CompanySearchDTO companySearchDTO, Long page, Integer size) {

		if(companySearchDTO == null) {
			companySearchDTO = new  CompanySearchDTO();
		}
		
		if (page == null) {
			page = 1L;
		}
		if (size == null) {
			size = 10;
		}
		List<CompanyDTO> list = new ArrayList<>();

		Map<String, Object> params = new HashedMap<>();
		if (!StringUtils.isEmpty(companySearchDTO.getSearchKey())) {
			params.put("searchKey", companySearchDTO.getSearchKey());
		}
		if (companySearchDTO.getStatus() != null) {
			params.put("status", companySearchDTO.getStatus());
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

	@Override
	public void addCompany(AddCompanyDTO addCompanyDTO) {
		
		CompanyExample example = new CompanyExample();
		example.createCriteria().andNameEqualTo(addCompanyDTO.getName());
		List<Company> list = companyDAO.selectByExample(example);
		
		if(CollectionUtils.isNotEmpty(list)) {
			throw new BizException("手机号已被注册");
		}
		
		example = new CompanyExample();
		example.createCriteria().andEmailEqualTo(addCompanyDTO.getEmail());
		list = companyDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			throw new BizException("Email已被注册");
		}
		
		example = new CompanyExample();
		example.createCriteria().andPhoneEqualTo(addCompanyDTO.getPhone());
		list = companyDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			throw new BizException("手机号已被注册");
		}
		
		Long date = new Date().getTime();
		
		Company company = new Company();
		company.setLastAccess(date);
		company.setName(addCompanyDTO.getName());
		company.setEmail(addCompanyDTO.getEmail());
		company.setJoinDate(date);
		company.setStatus(CompanyStatusEnum.pending.getId());
		company.setAttachment(addCompanyDTO.getAttachment());
		companyDAO.insertSelective(company);
	}

	@Override
	public CompanyDTO findById(Long id) {
		
		Company company = checkCompany(id);
		
		CompanyDTO companyDTO = new CompanyDTO();
		
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		companyDTO.setCity(company.getCity());
		companyDTO.setJoinDate(DateUtils.longToString(company.getJoinDate(),null));
		companyDTO.setStatus(CompanyStatusEnum.getValueById(company.getStatus()));
		companyDTO.setAddress(company.getAddress());
		companyDTO.setField(company.getField());
		companyDTO.setScale(company.getScale());
		companyDTO.setWebsite(company.getWebsite());
		companyDTO.setEmail(company.getEmail());
		companyDTO.setNote(company.getNote());
		companyDTO.setDescription(company.getDescription());
		
		return companyDTO;
	}

	@Override
	public void update(CompanyDTO companyDTO) {
		
		Company company = checkCompany(companyDTO.getId());
		
		if(!StringUtils.isEmpty(companyDTO.getName())) {
			CompanyExample example = new CompanyExample();
			example.createCriteria().andNameEqualTo(company.getName());
			List<Company> list = companyDAO.selectByExample(example);
			if(CollectionUtils.isNotEmpty(list)) {
				if(list.get(0).getId().longValue() != companyDTO.getId().longValue()) {
					throw new BizException("公司名称已注册");
				}
			}
		}
		
		Company newCompany = new Company();
		newCompany.setId(company.getId());
		newCompany.setLastAccess(new Date().getTime());
		newCompany.setName(companyDTO.getName());
		newCompany.setWebsite(companyDTO.getWebsite());
		newCompany.setCity(companyDTO.getCity());
		newCompany.setField(companyDTO.getField());
		newCompany.setScale(companyDTO.getScale());
		newCompany.setNote(companyDTO.getNote());
		newCompany.setDescription(companyDTO.getDescription());
		
		companyDAO.updateByPrimaryKeySelective(newCompany);
	}
	
	private Company checkCompany(Long id) {
		Company company = companyDAO.selectByPrimaryKey(id);
		if(company == null) {
			throw new BizException("未找到企业，请刷新页面");
		}
		return company;
	}

	@Override
	public void passCompany(Long id) {
		checkCompany(id);
		Company newCompany = new Company();
		newCompany.setId(id);
		newCompany.setLastAccess(new Date().getTime());
		newCompany.setStatus(CompanyStatusEnum.audited.getId());
		companyDAO.updateByPrimaryKeySelective(newCompany);
	}

	@Override
	public void removeCompany(Long id) {
		checkCompany(id);
		Company newCompany = new Company();
		newCompany.setId(id);
		newCompany.setLastAccess(new Date().getTime());
		newCompany.setStatus(CompanyStatusEnum.remove.getId());
		companyDAO.updateByPrimaryKeySelective(newCompany);
	}

}
