package com.yzhh.backstage.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.AuditDTO;
import com.yzhh.backstage.api.dto.LoginDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.account.AccountDTO;
import com.yzhh.backstage.api.dto.account.AccountLogDTO;
import com.yzhh.backstage.api.dto.admin.AdminDTO;
import com.yzhh.backstage.api.dto.admin.AdminPoorDTO;
import com.yzhh.backstage.api.dto.admin.AmountSettingDTO;
import com.yzhh.backstage.api.dto.admin.EditJurisdictionDTO;
import com.yzhh.backstage.api.dto.admin.LogDTO;
import com.yzhh.backstage.api.dto.company.AddCompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanySearchDTO;
import com.yzhh.backstage.api.dto.position.PositionDTO;
import com.yzhh.backstage.api.dto.position.SearchPositionDTO;
import com.yzhh.backstage.api.dto.resume.AddInterviewDTO;
import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.dto.resume.ResumeSearchDTO;
import com.yzhh.backstage.api.enums.AccountTypeEnum;
import com.yzhh.backstage.api.enums.DeliveryResumeStatusEnum;
import com.yzhh.backstage.api.enums.JurisdictionEnum;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.service.IAccountService;
import com.yzhh.backstage.api.service.IAdminService;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.service.ILogService;
import com.yzhh.backstage.api.service.IPositionService;
import com.yzhh.backstage.api.service.IResumeService;
import com.yzhh.backstage.api.util.ValidateUtil;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	private IAdminService adminService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IResumeService resumeService;
	@Autowired
	private ILogService logService;
	@Autowired
	private IAccountService accountService;
	

	@ApiOperation(value = "登录", notes = "", tags = { "管理员部分api" })
	@PostMapping("/login")
	public ApiResponse adminLogin(HttpServletRequest request, @RequestBody @Valid LoginDTO loginDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO a = adminService.login(loginDTO);
		String ip = (String) request.getSession().getAttribute(Constants.IP);

		logService.addLog(a.getId(), a.getName(), ip, a.getName() + "登录系统");

		return new ApiResponse(a);
	}

	@ApiOperation(value = "登出", notes = "", tags = { "管理员部分api" })
	@GetMapping("/login/out")
	public ApiResponse adminLoginOut(HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "登出系统");

		request.getSession().setAttribute(Constants.USER_LOGIN_SESSION, null);

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
	public ApiResponse saveOrUpdate(HttpServletRequest request, @RequestBody @Valid AdminDTO adminDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		adminService.saveOrUpdate(adminDTO);

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		if (adminDTO.getId() == null) {
			logService.addLog(user.getId(), user.getName(), ip, user.getName() + "添加管理员" + adminDTO.getName());
		} else {
			logService.addLog(user.getId(), user.getName(), ip, user.getName() + "编辑管理原" + adminDTO.getName());
		}

		return new ApiResponse();
	}

	@ApiOperation(value = "修改用户权限", notes = "", tags = { "管理员部分api" })
	@PostMapping("/save/jurisdiction")
	public ApiResponse updateJurisdiction(HttpServletRequest request,
			@RequestBody @Valid EditJurisdictionDTO editJurisdictionDTO, BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		adminService.updateJurisdiction(editJurisdictionDTO);
		AdminPoorDTO adminDTO = adminService.findByID(editJurisdictionDTO.getId());

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "编辑" + adminDTO.getName() + "管理员权限");

		return new ApiResponse();
	}

	@ApiOperation(value = "删除管理员用户", notes = "", tags = { "管理员部分api" })
	@DeleteMapping("/delete/{id}")
	public ApiResponse delete(HttpServletRequest request, @PathVariable Long id) {

		AdminPoorDTO adminDTO = adminService.findByID(id);
		
		adminService.delete(id);

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "删除" + adminDTO.getName() + "管理员");

		return new ApiResponse();
	}

	@ApiOperation(value = "分页查询用户日志", notes = "", tags = { "管理员部分api" })
	@GetMapping("/list/log/{id}")
	public ApiResponse queryByPageLog(HttpServletRequest request, @PathVariable Long id, Long page, Integer size) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		checkRole(JurisdictionEnum.search_log.getId(), user.getJurisdiction());

		PageDTO<LogDTO> p = adminService.queryByPageLog(id, page, size);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "删除所有用户日志", notes = "", tags = { "管理员部分api" })
	@DeleteMapping("/delete/log/{id}")
	public ApiResponse deleteLog(@PathVariable Long id) {

		adminService.deleteByOpratorId(id);

		return new ApiResponse();
	}

	@ApiOperation(value = "企业列表", notes = "", tags = { "管理员部分api" })
	@PostMapping("/company/list")
	public ApiResponse companyList(@RequestBody CompanySearchDTO companySearchDTO, Long page, Integer size) {

		PageDTO<CompanyDTO> list = companyService.queryByPage(companySearchDTO, page, size);

		return new ApiResponse(list);
	}

	@ApiOperation(value = "新增企业", notes = "", tags = { "管理员部分api" })
	@PostMapping("/add/company")
	public ApiResponse addCompany(HttpServletRequest request, @RequestBody @Valid AddCompanyDTO addCompanyDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		checkRole(JurisdictionEnum.add_company.getId(), user.getJurisdiction());

		companyService.addCompany(addCompanyDTO);

		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "新增企业" + addCompanyDTO.getName() + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "修改企业信息", notes = "修改基础信息和修改description是一个接口。传的值不同", tags = { "管理员部分api" })
	@PostMapping("/update/company")
	public ApiResponse updateCompany(HttpServletRequest request, @RequestBody @Valid CompanyDTO companyDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		checkRole(JurisdictionEnum.edit_company.getId(), user.getJurisdiction());

		companyService.update(companyDTO);

		companyDTO = companyService.findById(companyDTO.getId());
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "编辑企业" + companyDTO.getName() + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "企业详情", notes = "", tags = { "管理员部分api" })
	@GetMapping("/company/{id}")
	public ApiResponse updateCompany(@PathVariable Long id) {

		CompanyDTO companyDTO = companyService.findById(id);

		return new ApiResponse(companyDTO);
	}

	@ApiOperation(value = "审核通过企业", notes = "", tags = { "管理员部分api" })
	@PutMapping("/company/pass/{id}")
	public ApiResponse passCompany(HttpServletRequest request, @PathVariable Long id) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		checkRole(JurisdictionEnum.audit_company.getId(), user.getJurisdiction());

		companyService.passCompany(id);

		CompanyDTO companyDTO = companyService.findById(id);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "审核通过企业" + companyDTO.getName() + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "移除企业", notes = "", tags = { "管理员部分api" })
	@PutMapping("/company/remove/{id}")
	public ApiResponse removeCompany(HttpServletRequest request, @PathVariable Long id) {

		companyService.removeCompany(id);

		CompanyDTO companyDTO = companyService.findById(id);
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "移除企业" + companyDTO.getName() + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "保存职位", notes = "新增和修改职位通用，区别在于职位id是否有传", tags = { "管理员部分api" })
	@PostMapping("/save/position")
	public ApiResponse saveOrUpdatePosition(HttpServletRequest request, @RequestBody @Valid PositionDTO positionDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		if (positionDTO.getId() == null) {
			checkRole(JurisdictionEnum.add_position.getId(), user.getJurisdiction());
			logService.addLog(user.getId(), user.getName(), ip, user.getName() + "新增职位" + positionDTO.getName() + "");
		} else {
			checkRole(JurisdictionEnum.edit_position.getId(), user.getJurisdiction());
			logService.addLog(user.getId(), user.getName(), ip, user.getName() + "编辑职位" + positionDTO.getName() + "");
		}

		positionService.saveOrUpdatePosition(positionDTO);

		return new ApiResponse();
	}

	@ApiOperation(value = "职位列表", notes = "", tags = { "管理员部分api" })
	@PostMapping("/position/list")
	public ApiResponse positionList(@RequestBody SearchPositionDTO searchPositionDTO, Long page, Integer size) {

		PageDTO<PositionDTO> p = positionService.list(searchPositionDTO, page, size,null);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "职位详情", notes = "", tags = { "管理员部分api" })
	@GetMapping("/position/{id}")
	public ApiResponse positionInfo(@PathVariable Long id) {

		PositionDTO positionDTO = positionService.findById(id,null);

		return new ApiResponse(positionDTO);
	}

	@ApiOperation(value = "审核职位", notes = "", tags = { "管理员部分api" })
	@PutMapping("/position/audit")
	public ApiResponse passPosition(HttpServletRequest request, @RequestBody @Valid AuditDTO auditDTO,BindingResult result) {
		
		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		checkRole(JurisdictionEnum.audit_position.getId(), user.getJurisdiction());

		positionService.passPosition(auditDTO);

		PositionDTO positionDTO = positionService.findById(auditDTO.getId(),null);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "审核"+auditDTO.getStatus()+"职位" + positionDTO.getName() + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "下线职位", notes = "", tags = { "管理员部分api" })
	@PutMapping("/position/downline")
	public ApiResponse downLinePosition(HttpServletRequest request, @RequestBody List<Long> ids) {

		positionService.downLine(ids);

		String name = positionService.getName(ids);
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "下线职位" + name + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "删除职位", notes = "", tags = { "管理员部分api" })
	@PutMapping("/position/remove")
	public ApiResponse deletePosition(HttpServletRequest request, @RequestBody List<Long> ids) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		checkRole(JurisdictionEnum.delete_position.getId(), user.getJurisdiction());

		positionService.downLine(ids);

		String name = positionService.getName(ids);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "删除职位" + name + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "简历列表", notes = "这个对接的时候再说", tags = { "管理员部分api" })
	@PostMapping("/resume/list")
	public ApiResponse resumeList(HttpServletRequest request, @RequestBody ResumeSearchDTO resumeSearchDTO, Long page,
			Integer size) {

		PageDTO<PageResumeDTO> p = resumeService.queryPage(resumeSearchDTO, page, size);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "批量查看简历", notes = "", tags = { "管理员部分api" })
	@PutMapping("/resume/look")
	public ApiResponse lockResume(HttpServletRequest request, @RequestBody List<Long> ids) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		checkRole(JurisdictionEnum.mark_job.getId(), user.getJurisdiction());

		resumeService.updateStatus(ids, DeliveryResumeStatusEnum.look.getId());

		String name = resumeService.getName(ids);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "查看简历" + name + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "批量待定简历", notes = "", tags = { "管理员部分api" })
	@PutMapping("/resume/pending")
	public ApiResponse pendingResume(HttpServletRequest request, @RequestBody List<Long> ids) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		checkRole(JurisdictionEnum.mark_job.getId(), user.getJurisdiction());

		resumeService.updateStatus(ids, DeliveryResumeStatusEnum.pending.getId());

		String name = resumeService.getName(ids);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "待定简历" + name + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "批量不合适简历", notes = "", tags = { "管理员部分api" })
	@PutMapping("/resume/not")
	public ApiResponse notRightResume(HttpServletRequest request, @RequestBody List<Long> ids) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		checkRole(JurisdictionEnum.mark_job.getId(), user.getJurisdiction());

		resumeService.updateStatus(ids, DeliveryResumeStatusEnum.not_right.getId());

		String name = resumeService.getName(ids);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "不合适简历" + name + "");

		return new ApiResponse();
	}

	@ApiOperation(value = "邀请面试", notes = "", tags = { "管理员部分api" })
	@PostMapping("/interview/invitation")
	public ApiResponse interviewInvitation(HttpServletRequest request,
			@RequestBody @Valid AddInterviewDTO addInterviewDTO, BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		checkRole(JurisdictionEnum.notice_interview.getId(), user.getJurisdiction());

		resumeService.interviewInvitation(addInterviewDTO);

		List<Long> ids = new ArrayList<>();
		ids.add(addInterviewDTO.getDeliveryResumeId());
		String name = resumeService.getName(ids);
		String ip = (String) request.getSession().getAttribute(Constants.IP);
		logService.addLog(user.getId(), user.getName(), ip, user.getName() + "邀请简历" + name + "面试");

		return new ApiResponse();
	}

	@ApiOperation(value = "获取公司账户信息", notes = "", tags = { "管理员部分api" })
	@GetMapping("/company/account/{id}")
	public ApiResponse getAccount(@PathVariable Long id) {

		AccountDTO accountDTO = accountService.getAccount(id, AccountTypeEnum.company.getId());

		return new ApiResponse(accountDTO);
	}
	
	@ApiOperation(value = "获取账户流水信息", notes = "", tags = { "管理员部分api" })
	@GetMapping("/account/log/{id}")
	public ApiResponse getAccountLog(@PathVariable Long id,Long page,Integer size) {

		PageDTO<AccountLogDTO> pageDTO = accountService.getAccountLogList(id,null, page, size);

		return new ApiResponse(pageDTO);
	}
	
	@ApiOperation(value = "金额配置列表", notes = "", tags = { "管理员部分api" })
	@GetMapping("/amount/setting/list")
	public ApiResponse amountSettingList() {

		List<AmountSettingDTO> list = adminService.getAmountSettingList();

		return new ApiResponse(list);
	}
	
	@ApiOperation(value = "配置金额", notes = "", tags = { "管理员部分api" })
	@PutMapping("/amount/setting")
	public ApiResponse amountSetting(HttpServletRequest request,@RequestBody @Valid AmountSettingDTO amountSettingDTO) {

		adminService.updateAmountSetting(amountSettingDTO);

		return new ApiResponse();
	}
	

	private boolean checkRole(int j, String jurisdictionStr) {
		if (StringUtils.isEmpty(jurisdictionStr)) {
			return false;
		}
		String[] str = jurisdictionStr.split(",");
		for (String s : str) {
			if (j == Integer.parseInt(s)) {
				return true;
			}
		}
		return false;
	}
}
