package com.yzhh.backstage.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.LoginDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.admin.AdminDTO;
import com.yzhh.backstage.api.dto.admin.EditJurisdictionDTO;
import com.yzhh.backstage.api.dto.admin.LogDTO;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.service.IAdminService;
import com.yzhh.backstage.api.util.ValidateUtil;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;
	
	@ApiOperation(value = "登录", notes = "", tags = { "管理员部分api" })
	@PostMapping("/login")
	public ApiResponse adminLogin(HttpServletRequest request ,@Valid LoginDTO loginDTO,BindingResult result) {
		
		if(result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		AdminDTO a = adminService.login(loginDTO);
		
		request.getSession().setAttribute(Constants.ADMIN_LOGIN, a);
		
		return new ApiResponse(a);
	}
	
	@ApiOperation(value = "登出", notes = "", tags = { "管理员部分api" })
	@PostMapping("/login/out")
	public ApiResponse adminLoginOut(HttpServletRequest request ) {
		
		request.getSession().setAttribute(Constants.ADMIN_LOGIN, null);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "管理员列表", notes = "", tags = { "管理员部分api" })
	@GetMapping("/list")
	public ApiResponse list() {
		
		List<AdminDTO> list = adminService.list();
		
		return new ApiResponse(list);
	}
	
	@ApiOperation(value = "保存管理员信息", notes = "新增和更新都走这个接口，区别在于有没有id", tags = { "管理员部分api" })
	@PostMapping("/save")
	public ApiResponse saveOrUpdate(@Valid AdminDTO adminDTO,BindingResult result) {
		
		if(result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		adminService.saveOrUpdate(adminDTO);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "修改用户权限", notes = "", tags = { "管理员部分api" })
	@PostMapping("/save/jurisdiction")
	public ApiResponse updateJurisdiction(@Valid EditJurisdictionDTO editJurisdictionDTO,BindingResult result) {
		
		if(result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		adminService.updateJurisdiction(editJurisdictionDTO);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "删除管理员用户", notes = "", tags = { "管理员部分api" })
	@DeleteMapping("/delete/{id}")
	public ApiResponse delete(@PathVariable Long id) {
		
		adminService.delete(id);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "分页查询用户日志", notes = "", tags = { "管理员部分api" })
	@GetMapping("/list/log/{id}")
	public ApiResponse queryByPageLog(@PathVariable Long id,Long page,Integer size) {
		
		PageDTO<LogDTO> p = adminService.queryByPageLog(id, page, size);
		
		return new ApiResponse(p);
	}
	
	@ApiOperation(value = "删除所有用户日志", notes = "", tags = { "管理员部分api" })
	@DeleteMapping("/delete/log/{id}")
	public ApiResponse deleteLog(@PathVariable Long id) {
		
		adminService.deleteByOpratorId(id);
		
		return new ApiResponse();
	}
	
	
	
}
