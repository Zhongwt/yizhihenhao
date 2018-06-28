package com.yzhh.backstage.api.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.dao.IAdminDAO;
import com.yzhh.backstage.api.dao.ILogDAO;
import com.yzhh.backstage.api.dto.LoginDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.admin.AdminDTO;
import com.yzhh.backstage.api.dto.admin.EditJurisdictionDTO;
import com.yzhh.backstage.api.dto.admin.LogDTO;
import com.yzhh.backstage.api.entity.Admin;
import com.yzhh.backstage.api.entity.AdminExample;
import com.yzhh.backstage.api.entity.LogExample;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.service.IAdminService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;
import com.yzhh.backstage.api.util.MD5;

@Service
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	private IAdminDAO adminDAO;
	@Autowired
	private ILogDAO logDAO;

	@Override
	public AdminDTO login(LoginDTO loginDTO) {
		
		Long date = new Date().getTime();
		String password = loginDTO.getPassword();
		password = MD5.getMD5(password);
		
		AdminExample example = new AdminExample();
		example.createCriteria().andUsernameEqualTo(loginDTO.getUsername());
		List<Admin> list = adminDAO.selectByExample(example);
		
		if(CollectionUtils.isNotEmpty(list)) {
			if(password.equals(list.get(0).getPassword())) {
				
				AdminDTO adminDTO = new AdminDTO();
				adminDTO.setId(list.get(0).getId());
				adminDTO.setName(list.get(0).getName());
				adminDTO.setJurisdiction(list.get(0).getJurisdiction());
				adminDTO.setRole(list.get(0).getRole());
				adminDTO.setEmail(list.get(0).getEmail());
				adminDTO.setJoinDate(DateUtils.longToString(list.get(0).getJoinDate(), null));
				adminDTO.setLastLoginDate(list.get(0).getLastLoginDate() == null ? "" : DateUtils.longToString(list.get(0).getLastLoginDate(), null) );
				
				Admin admin = new Admin();
				admin.setId(list.get(0).getId());
				admin.setLastAccess(date);
				admin.setLastLoginDate(date);
				adminDAO.updateByPrimaryKeySelective(admin);
				
				return adminDTO;
			}
		}
		throw new BizException(CommonError.USER_AUTH_ERROR);
	}

	@Override
	public List<AdminDTO> list() {
		
		List<AdminDTO> list = new ArrayList<>();
		
		List<Admin> adminList = adminDAO.selectByExample(new AdminExample());
		
		if(CollectionUtils.isNotEmpty(adminList)) {
			for(Admin admin : adminList) {
				AdminDTO adminDTO = new AdminDTO();
				adminDTO.setId(admin.getId());
				adminDTO.setName(admin.getName());
				adminDTO.setJurisdiction(admin.getJurisdiction());
				adminDTO.setRole(admin.getRole());
				adminDTO.setEmail(admin.getEmail());
				adminDTO.setJoinDate(DateUtils.longToString(admin.getJoinDate(), null));
				adminDTO.setLastLoginDate(admin.getLastLoginDate() == null ? "" : DateUtils.longToString(admin.getLastLoginDate(), null) );
				list.add(adminDTO);
			}
		}
		
		return list;
	}

	@Override
	public void saveOrUpdate(AdminDTO adminDTO) {
		
		long date = new Date().getTime();
		AdminExample example = new AdminExample();
		example.createCriteria().andUsernameEqualTo(adminDTO.getUserName());
		List<Admin> list = adminDAO.selectByExample(example);
		
		
		if(adminDTO.getId() != null) {
			Admin admin = adminDAO.selectByPrimaryKey(adminDTO.getId());
			if(admin == null) {
				throw new BizException("未找到用户，请刷新页面");
			}
			
			if(CollectionUtils.isNotEmpty(list)) {
				if(list.get(0).getId().longValue() != admin.getId().longValue()) {
					throw new BizException("用户名已存在，请重新输入用户名");
				}
			}
			admin.setLastAccess(date);
			admin.setName(adminDTO.getName());
			admin.setName(adminDTO.getName());
			admin.setUsername(admin.getUsername());
			admin.setPassword(MD5.getMD5(adminDTO.getPassword()));
			admin.setEmail(adminDTO.getEmail());
			admin.setJurisdiction(adminDTO.getJurisdiction());
			
			adminDAO.updateByPrimaryKeySelective(admin);
		}else {
			if(CollectionUtils.isNotEmpty(list)) {
				throw new BizException("用户名已存在，请重新输入用户名");
			}
			Admin admin = new Admin();
			admin.setLastAccess(date);
			admin.setName(adminDTO.getName());
			admin.setUsername(admin.getUsername());
			admin.setPassword(MD5.getMD5(adminDTO.getPassword()));
			admin.setEmail(adminDTO.getEmail());
			admin.setJoinDate(date);
			admin.setRole(0);
			admin.setJurisdiction(adminDTO.getJurisdiction());
			
			adminDAO.insertSelective(admin);
		}
	}

	@Override
	public void updateJurisdiction(EditJurisdictionDTO editJurisdictionDTO) {
		
		Admin admin = adminDAO.selectByPrimaryKey(editJurisdictionDTO.getId());
		if(admin == null) {
			throw new BizException("未找到用户，请刷新页面");
		}
		Admin newAdmin = new Admin();
		newAdmin.setId(editJurisdictionDTO.getId());
		newAdmin.setLastAccess(new Date().getTime());
		newAdmin.setJurisdiction(editJurisdictionDTO.getJurisdiction());
		adminDAO.updateByPrimaryKeySelective(admin);
	}

	@Override
	public void delete(Long id) {
		Admin admin = adminDAO.selectByPrimaryKey(id);
		if(admin == null) {
			throw new BizException("未找到用户，请刷新页面");
		}
		adminDAO.deleteByPrimaryKey(id);
	}

	@Override
	public PageDTO<LogDTO> queryByPageLog(Long id,Long page,Integer size) {
		
		Map<String,Object> params = new HashMap<>();
		if(page == null) {
			page = 1L;
		}
		if(size == null) {
			size = 10;
		}
		
		params.put("operatorId", id);
		params.put("starNum", (page-1)*size);
		params.put("size", size);
		
		List<LogDTO> list = logDAO.queryByPage(params);
		
		LogExample example = new LogExample();
		example.createCriteria().andOperatorIdEqualTo(id);
		
		Long count = logDAO.countByExample(example);
		
		PageDTO<LogDTO> p = new PageDTO<>();
		p.setPage(page);
		p.setSize(size);
		p.setList(list);
		p.setCount(count);
		
		return p;
	}

	@Override
	public void deleteByOpratorId(Long id) {
		LogExample example = new LogExample();
		example.createCriteria().andOperatorIdEqualTo(id);
		logDAO.deleteByExample(example);
	}

}
