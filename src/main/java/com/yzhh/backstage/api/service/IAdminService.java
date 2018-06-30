package com.yzhh.backstage.api.service;

import java.util.List;

import com.yzhh.backstage.api.dto.LoginDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.admin.AdminDTO;
import com.yzhh.backstage.api.dto.admin.AdminPoorDTO;
import com.yzhh.backstage.api.dto.admin.EditJurisdictionDTO;
import com.yzhh.backstage.api.dto.admin.LogDTO;

public interface IAdminService {

	//登录
	public UserDTO login(LoginDTO loginDTO);
	//管理员列表
	public List<AdminDTO> list();
	//新增或修改管理原
	public  void saveOrUpdate(AdminDTO adminDTO);
	//修改权限
	public void updateJurisdiction(EditJurisdictionDTO editJurisdictionDTO);
	//删除管理员
	public void delete(Long id);
	//分页查询用户日志
	public PageDTO<LogDTO>  queryByPageLog(Long id,Long page,Integer size);
	//删除用户日志
	public void deleteByOpratorId(Long id);
	//获取管理员信息
	public AdminPoorDTO findByID(Long id);
	
}
