package com.yzhh.backstage.api.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dao.IAdminDAO;
import com.yzhh.backstage.api.dao.IAmountSettingDAO;
import com.yzhh.backstage.api.dao.ILogDAO;
import com.yzhh.backstage.api.dto.LoginDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.admin.AdminDTO;
import com.yzhh.backstage.api.dto.admin.AdminPoorDTO;
import com.yzhh.backstage.api.dto.admin.AmountSettingDTO;
import com.yzhh.backstage.api.dto.admin.EditJurisdictionDTO;
import com.yzhh.backstage.api.dto.admin.LogDTO;
import com.yzhh.backstage.api.entity.Admin;
import com.yzhh.backstage.api.entity.AdminExample;
import com.yzhh.backstage.api.entity.AmountSetting;
import com.yzhh.backstage.api.entity.AmountSettingExample;
import com.yzhh.backstage.api.entity.LogExample;
import com.yzhh.backstage.api.enums.RoleEnum;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.service.IAdminService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;
import com.yzhh.backstage.api.util.MD5;
import com.yzhh.backstage.api.util.RedisUtil;

@Service
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	private IAdminDAO adminDAO;
	@Autowired
	private ILogDAO logDAO;
	@Autowired
	private IAmountSettingDAO amountSettingDAO;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public UserDTO login(LoginDTO loginDTO) {
		
		Long date = new Date().getTime();
		String password = loginDTO.getPassword();
		password = MD5.getMD5(password);
		
		AdminExample example = new AdminExample();
		example.createCriteria().andUsernameEqualTo(loginDTO.getUsername());
		List<Admin> list = adminDAO.selectByExample(example);
		
		if(CollectionUtils.isNotEmpty(list)) {
			if(password.equals(list.get(0).getPassword())) {
				
				Admin admin = new Admin();
				admin.setId(list.get(0).getId());
				admin.setLastAccess(date);
				admin.setLastLoginDate(date);
				adminDAO.updateByPrimaryKeySelective(admin);
				
				UserDTO adminDTO = new UserDTO();
				adminDTO.setId(list.get(0).getId());
				adminDTO.setName(list.get(0).getName());
				adminDTO.setJurisdiction(list.get(0).getJurisdiction());
				adminDTO.setRole(RoleEnum.admin.getId());
				adminDTO.setEmail(list.get(0).getEmail());
				
				redisUtil.set(Constants.USER_LOGIN +adminDTO.getId(), adminDTO,Constants.TWO_HOUR);
				
				return adminDTO;
			}
		}
		throw new BizException(CommonError.USER_AUTH_ERROR);
	}

	@Override
	public List<AdminDTO> list() {
		
		List<AdminDTO> list = new ArrayList<>();
		AdminExample example = new AdminExample();
		example.createCriteria().andIdNotEqualTo(1L);
		
		List<Admin> adminList = adminDAO.selectByExample(example);
		
		if(CollectionUtils.isNotEmpty(adminList)) {
			for(Admin admin : adminList) {
				AdminDTO adminDTO = new AdminDTO();
				adminDTO.setId(admin.getId());
				adminDTO.setName(admin.getName());
				adminDTO.setUsername(admin.getUsername());
				adminDTO.setJurisdiction(admin.getJurisdiction());
				adminDTO.setRole(admin.getRole());
				adminDTO.setPassword("123456");
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
		example.createCriteria().andUsernameEqualTo(adminDTO.getUsername());
		List<Admin> list = adminDAO.selectByExample(example);
		
		if(StringUtils.isEmpty(adminDTO.getJurisdiction())) {
			adminDTO.setJurisdiction("1,2,3,4,5,6,7,8,9,10");
		}
		
		if(adminDTO.getId() != null) {
			Admin admin = checkAdmin(adminDTO.getId());
			
			if(CollectionUtils.isNotEmpty(list)) {
				if(list.get(0).getId().longValue() != admin.getId().longValue()) {
					throw new BizException("用户名已存在，请重新输入用户名");
				}
			}
			admin.setLastAccess(date);
			admin.setName(adminDTO.getName());
			admin.setUsername(adminDTO.getUsername());
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
			admin.setUsername(adminDTO.getUsername());
			admin.setPassword(MD5.getMD5(adminDTO.getPassword()));
			admin.setEmail(adminDTO.getEmail());
			admin.setJoinDate(date);
			admin.setRole(0);
			admin.setJurisdiction(adminDTO.getJurisdiction());
			
			adminDAO.insertSelective(admin);
		}
	}
	
	private Admin checkAdmin(Long id) {
		Admin admin = adminDAO.selectByPrimaryKey(id);
		if(admin == null) {
			throw new BizException("未找到用户，请刷新页面");
		}
		return admin;
	}

	@Override
	public void updateJurisdiction(EditJurisdictionDTO editJurisdictionDTO) {
		
		Admin admin = checkAdmin(editJurisdictionDTO.getId());
		Admin newAdmin = new Admin();
		newAdmin.setId(editJurisdictionDTO.getId());
		newAdmin.setLastAccess(new Date().getTime());
		newAdmin.setJurisdiction(editJurisdictionDTO.getJurisdiction());
		adminDAO.updateByPrimaryKeySelective(admin);
	}

	@Override
	public void delete(Long id) {
		checkAdmin(id);
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

	@Override
	public AdminPoorDTO findByID(Long id) {
		
		Admin admin = checkAdmin(id);
		
		AdminPoorDTO adminPoor = new AdminPoorDTO();
		
		adminPoor.setId(admin.getId());
		adminPoor.setName(admin.getName());
		
		return adminPoor;
	}

	@Override
	public List<AmountSettingDTO> getAmountSettingList() {
		List<AmountSettingDTO> list = new ArrayList<>();
		List<AmountSetting> amountSettings = amountSettingDAO.selectByExample(new AmountSettingExample());
		if(CollectionUtils.isNotEmpty(amountSettings)) {
			for(AmountSetting amountSetting : amountSettings) {
				AmountSettingDTO e = new AmountSettingDTO();
				e.setId(amountSetting.getId());
				e.setType(amountSetting.getType());
				e.setAmount(amountSetting.getAmount());
				list.add(e);
			}
		}
		return list;
	}

	@Override
	public void updateAmountSetting(AmountSettingDTO amountSettingDTO) {
		if(amountSettingDTO.getAmount() < 0) {
			throw new BizException("设置金额必须大于0");
		}
		AmountSetting amountSetting = amountSettingDAO.selectByPrimaryKey(amountSettingDTO.getId());
		if(amountSetting == null) {
			throw new BizException("未找到金额设置");
		}
		amountSetting.setLastAccess(new Date().getTime());
		amountSetting.setAmount(amountSettingDTO.getAmount());
		
		amountSettingDAO.updateByPrimaryKeySelective(amountSetting);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
