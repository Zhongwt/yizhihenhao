package com.yzhh.backstage.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.ForgetPasswordDTO;
import com.yzhh.backstage.api.dto.LoginDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UpdatePasswordDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.company.AddCompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanyNoticeDTO;
import com.yzhh.backstage.api.dto.company.DescriptionDTO;
import com.yzhh.backstage.api.dto.company.StatisticsDTO;
import com.yzhh.backstage.api.dto.position.PositionDTO;
import com.yzhh.backstage.api.dto.position.SearchPositionDTO;
import com.yzhh.backstage.api.dto.resume.AddInterviewDTO;
import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.dto.resume.ResumeSearchDTO;
import com.yzhh.backstage.api.enums.CompanyStatusEnum;
import com.yzhh.backstage.api.enums.DeliveryResumeStatusEnum;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.service.IPositionService;
import com.yzhh.backstage.api.service.IResumeService;
import com.yzhh.backstage.api.util.ValidateUtil;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api/company")
public class CompanyController {

	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IResumeService resumeService;

	@ApiOperation(value = "企业找回并重置密码", notes = "", tags = { "企业部分api" })
	@PostMapping("/forget/password")
	public ApiResponse companyForgetPassword(@RequestBody @Valid ForgetPasswordDTO forgetPasswordDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		companyService.forgetPassword(forgetPasswordDTO);

		return new ApiResponse();
	}

	@ApiOperation(value = "企业加盟", notes = "", tags = { "企业部分api" })
	@PostMapping("/add")
	public ApiResponse addCompany(HttpServletRequest request, @RequestBody @Valid AddCompanyDTO addCompanyDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		companyService.addCompany(addCompanyDTO);

		return new ApiResponse();
	}

	@ApiOperation(value = "企业登录", notes = "", tags = { "企业部分api" })
	@PostMapping("/login")
	public ApiResponse companyLogin(HttpServletRequest request, @RequestBody @Valid LoginDTO loginDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO companyDTO = companyService.login(loginDTO);

		return new ApiResponse(companyDTO);
	}

	@ApiOperation(value = "企业首页统计", notes = "", tags = { "企业部分api" })
	@GetMapping("/statistics")
	public ApiResponse companyStatistics(HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		StatisticsDTO statisticsDTO = companyService.getCompanyStatistics(user.getId());

		return new ApiResponse(statisticsDTO);
	}

	@ApiOperation(value = "企业详细信息", notes = "", tags = { "企业部分api" })
	@GetMapping("/info")
	public ApiResponse companyInfo(HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		CompanyDTO companyDTO = companyService.findById(user.getId());

		return new ApiResponse(companyDTO);
	}

	@ApiOperation(value = "企业通知列表", notes = "", tags = { "企业部分api" })
	@GetMapping("/notice/info")
	public ApiResponse companyNoticeList(HttpServletRequest request, Long page, Integer size) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		PageDTO<CompanyNoticeDTO> pageDTO = companyService.getNoticeList(user.getId(), page, size);

		return new ApiResponse(pageDTO);
	}

	@ApiOperation(value = "企业未读通知数量", notes = "", tags = { "企业部分api" })
	@GetMapping("/notice/count")
	public ApiResponse companyNoticeCount(HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		Long count = companyService.getCompanyNoticeCount(user.getId());

		return new ApiResponse(count);
	}

	@ApiOperation(value = "修改公司基础信息", notes = "", tags = { "企业部分api" })
	@PostMapping("/save")
	public ApiResponse updateCompany(HttpServletRequest request, @RequestBody @Validated CompanyDTO companyDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		companyDTO.setId(user.getId());

		companyService.update(companyDTO);

		return new ApiResponse();
	}

	@ApiOperation(value = "修改公司描述", notes = "", tags = { "企业部分api" })
	@PostMapping("/save/description")
	public ApiResponse updateCompanyDescription(HttpServletRequest request,
			@RequestBody @Validated DescriptionDTO dcescriptionDTO, BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		companyService.updateDescription(user.getId(), dcescriptionDTO);

		return new ApiResponse();
	}

	@ApiOperation(value = "修改公司密码", notes = "", tags = { "企业部分api" })
	@PostMapping("/save/password")
	public ApiResponse updateCompanyPassword(HttpServletRequest request,
			@RequestBody @Validated UpdatePasswordDTO updatePasswordDTO, BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		companyService.updatePassword(user.getId(), updatePasswordDTO);

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
		if (user.getStatus().equals(CompanyStatusEnum.audited.getName())) {
			throw new BizException("公司无权操作职位");
		}

		positionDTO.setCompanyId(user.getId());
		positionService.saveOrUpdatePosition(positionDTO);

		return new ApiResponse();
	}

	@ApiOperation(value = "职位列表", notes = "", tags = { "管理员部分api" })
	@PostMapping("/position/list")
	public ApiResponse positionList(HttpServletRequest request, @RequestBody SearchPositionDTO searchPositionDTO,
			Long page, Integer size) {

		PageDTO<PositionDTO> p = positionService.list(searchPositionDTO, page, size);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "职位详情", notes = "", tags = { "管理员部分api" })
	@GetMapping("/position/{id}")
	public ApiResponse positionInfo(@PathVariable Long id) {

		PositionDTO positionDTO = positionService.findById(id,null);

		return new ApiResponse(positionDTO);
	}

	@ApiOperation(value = "下线职位", notes = "", tags = { "管理员部分api" })
	@PutMapping("/position/downline")
	public ApiResponse downLinePosition(HttpServletRequest request, @RequestBody List<Long> ids) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		if (user.getStatus().equals(CompanyStatusEnum.audited.getName())) {
			throw new BizException("公司无权操作职位");
		}

		positionService.downLine(ids);

		return new ApiResponse();
	}

	@ApiOperation(value = "简历列表", notes = "这个对接的时候再说", tags = { "管理员部分api" })
	@PostMapping("/resume/list")
	public ApiResponse resumeList(HttpServletRequest request, @RequestBody ResumeSearchDTO resumeSearchDTO, Long page,
			Integer size) {

		if (resumeSearchDTO == null) {
			resumeSearchDTO = new ResumeSearchDTO();
		}
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		resumeSearchDTO.setCompanyId(user.getId());
		PageDTO<PageResumeDTO> p = resumeService.queryPage(resumeSearchDTO, page, size);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "简历详情", notes = "", tags = { "管理员部分api" })
	@PostMapping("/resume/{deliveryResumeId}")
	public ApiResponse resumeList(HttpServletRequest request, @PathVariable Long deliveryResumeId) {

		return new ApiResponse();
	}

	@ApiOperation(value = "批量查看简历", notes = "", tags = { "管理员部分api" })
	@PutMapping("/resume/look")
	public ApiResponse lockResume(HttpServletRequest request, @RequestBody List<Long> ids) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		if (user.getStatus().equals(CompanyStatusEnum.audited.getName())) {
			throw new BizException("公司无权操作简历");
		}

		resumeService.updateStatus(ids, DeliveryResumeStatusEnum.look.getId());

		return new ApiResponse();
	}

	@ApiOperation(value = "批量待定简历", notes = "", tags = { "管理员部分api" })
	@PutMapping("/resume/pending")
	public ApiResponse pendingResume(HttpServletRequest request, @RequestBody List<Long> ids) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		if (user.getStatus().equals(CompanyStatusEnum.audited.getName())) {
			throw new BizException("公司无权操作简历");
		}

		resumeService.updateStatus(ids, DeliveryResumeStatusEnum.pending.getId());

		return new ApiResponse();
	}

	@ApiOperation(value = "批量不合适简历", notes = "", tags = { "管理员部分api" })
	@PutMapping("/resume/not")
	public ApiResponse notRightResume(HttpServletRequest request, @RequestBody List<Long> ids) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		if (user.getStatus().equals(CompanyStatusEnum.audited.getName())) {
			throw new BizException("公司无权操作简历");
		}

		resumeService.updateStatus(ids, DeliveryResumeStatusEnum.not_right.getId());

		return new ApiResponse();
	}

	@ApiOperation(value = "邀请面试", notes = "", tags = { "管理员部分api" })
	@PostMapping("/interview/invitation")
	public ApiResponse interviewInvitation(HttpServletRequest request,
			@RequestBody @Valid AddInterviewDTO addInterviewDTO, BindingResult result) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		if (user.getStatus().equals(CompanyStatusEnum.audited.getName())) {
			throw new BizException("公司无权邀请面试");
		}

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		resumeService.interviewInvitation(addInterviewDTO);

		return new ApiResponse();
	}

	public ApiResponse resumeList(HttpServletRequest request) {
		return null;
	}

}
