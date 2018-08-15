package com.yzhh.backstage.api.controller;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.ForgetPasswordDTO;
import com.yzhh.backstage.api.dto.LoginDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UpdatePasswordDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.company.ApplyCompany;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanyNoticeDTO;
import com.yzhh.backstage.api.dto.company.RegisterCompany;
import com.yzhh.backstage.api.dto.company.StatementDTO;
import com.yzhh.backstage.api.dto.company.StatisticsDTO;
import com.yzhh.backstage.api.dto.company.UpdateCompanyDTO;
import com.yzhh.backstage.api.dto.position.PositionDTO;
import com.yzhh.backstage.api.dto.position.SearchPositionDTO;
import com.yzhh.backstage.api.dto.resume.AddInterviewDTO;
import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.dto.resume.ResumeDTO;
import com.yzhh.backstage.api.dto.resume.ResumeLibDTO;
import com.yzhh.backstage.api.dto.resume.ResumeSearchDTO;
import com.yzhh.backstage.api.enums.AccountTypeEnum;
import com.yzhh.backstage.api.enums.CompanyStatusEnum;
import com.yzhh.backstage.api.enums.DeliveryResumeStatusEnum;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.service.IPositionService;
import com.yzhh.backstage.api.service.IResumeService;
import com.yzhh.backstage.api.util.QrCide;
import com.yzhh.backstage.api.util.RedisUtil;
import com.yzhh.backstage.api.util.ValidateUtil;
import com.yzhh.backstage.api.util.WeChatHttpUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	@Autowired
	private RedisUtil redisUtil;

	private Logger logger = LoggerFactory.getLogger(CompanyController.class);

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

	@ApiOperation(value = "企业注册", notes = "", tags = { "企业部分api" })
	@PostMapping("/register")
	public ApiResponse registerCompany(HttpServletRequest request, @RequestBody @Valid RegisterCompany registerCompany,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		companyService.registerCompany(registerCompany);

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

	@ApiOperation(value = "企业申请审核", notes = "", tags = { "企业部分api" })
	@PostMapping("/apply")
	public ApiResponse companyApply(HttpServletRequest request, @RequestBody @Valid ApplyCompany applyCompany,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		companyService.applyCompany(user.getId(), applyCompany);

		return new ApiResponse();
	}

	@ApiOperation(value = "企业申述", notes = "", tags = { "企业部分api" })
	@PostMapping("/statement")
	public ApiResponse companyStatement(HttpServletRequest request, @RequestBody @Valid StatementDTO statementDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		companyService.statmentCompany(user.getId(), statementDTO);

		return new ApiResponse();
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
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "long", name = "page", value = "页码"),
			@ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "页面大小") })
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
	public ApiResponse updateCompany(HttpServletRequest request,
			@RequestBody @Validated UpdateCompanyDTO updateCompanyDTO, BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		companyService.update(user.getId(), updateCompanyDTO);

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

	@ApiOperation(value = "保存职位", notes = "新增和修改职位通用，区别在于职位id是否有传", tags = { "企业部分api" })
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

	@ApiOperation(value = "职位列表", notes = "", tags = { "企业部分api" })
	@PostMapping("/position/list")
	public ApiResponse positionList(HttpServletRequest request, @RequestBody SearchPositionDTO searchPositionDTO,
			Long page, Integer size) {

		PageDTO<PositionDTO> p = positionService.list(searchPositionDTO, page, size, null);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "职位详情", notes = "", tags = { "企业部分api" })
	@GetMapping("/position/{id}")
	public ApiResponse positionInfo(@PathVariable Long id) {

		PositionDTO positionDTO = positionService.findById(id, null);

		return new ApiResponse(positionDTO);
	}

	@ApiOperation(value = "下线职位", notes = "", tags = { "企业部分api" })
	@PutMapping("/position/downline")
	public ApiResponse downLinePosition(HttpServletRequest request, @RequestBody List<Long> ids) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		if (user.getStatus().equals(CompanyStatusEnum.audited.getName())) {
			throw new BizException("公司无权操作职位");
		}

		positionService.downLine(ids);

		return new ApiResponse();
	}

	@ApiOperation(value = "投递过来的简历列表", notes = "这个对接的时候再说", tags = { "企业部分api" })
	@PostMapping("/delivery/resume/list")
	public ApiResponse deliveryResumeList(HttpServletRequest request, @RequestBody ResumeSearchDTO resumeSearchDTO,
			Long page, Integer size) {

		if (resumeSearchDTO == null) {
			resumeSearchDTO = new ResumeSearchDTO();
		}
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		resumeSearchDTO.setCompanyId(user.getId());
		PageDTO<PageResumeDTO> p = resumeService.queryPage(resumeSearchDTO, page, size);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "投递过来的简历详情", notes = "", tags = { "企业部分api" })
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "path", dataType = "long", name = "deliveryResumeId", value = "投递简历id") })
	@PostMapping("/delivery/resume/{deliveryResumeId}")
	public ApiResponse deliveryResumeInfo(HttpServletRequest request, @PathVariable Long deliveryResumeId) {

		ResumeDTO resumeDTO = resumeService.conmpanyGetDeliveryResume(deliveryResumeId);

		return new ApiResponse(resumeDTO);
	}

	@ApiOperation(value = "批量查看简历", notes = "", tags = { "企业部分api" })
	@PutMapping("/resume/look")
	public ApiResponse lockResume(HttpServletRequest request, @RequestBody List<Long> ids) {

		resumeService.updateStatus(ids, DeliveryResumeStatusEnum.look.getId());

		return new ApiResponse();
	}

	@ApiOperation(value = "批量待定简历", notes = "", tags = { "企业部分api" })
	@PutMapping("/resume/pending")
	public ApiResponse pendingResume(HttpServletRequest request, @RequestBody List<Long> ids) {

		resumeService.updateStatus(ids, DeliveryResumeStatusEnum.pending.getId());

		return new ApiResponse();
	}

	@ApiOperation(value = "批量不合适简历", notes = "", tags = { "企业部分api" })
	@PutMapping("/resume/not")
	public ApiResponse notRightResume(HttpServletRequest request, @RequestBody List<Long> ids) {

		resumeService.updateStatus(ids, DeliveryResumeStatusEnum.not_right.getId());

		return new ApiResponse();
	}

	@ApiOperation(value = "邀请面试", notes = "", tags = { "企业部分api" })
	@PostMapping("/interview/invitation")
	public ApiResponse interviewInvitation(HttpServletRequest request,
			@RequestBody @Valid AddInterviewDTO addInterviewDTO, BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}

		resumeService.interviewInvitation(addInterviewDTO);

		return new ApiResponse();
	}

	@ApiOperation(value = "简历列表，企业自主查询的简历，不是用户投递过来的简历", notes = "", tags = { "企业部分api" })
	@PostMapping("/resume/list")
	public ApiResponse resumeList(HttpServletRequest request, @RequestBody ResumeSearchDTO resumeSearchDTO, Long page,
			Integer size) {

		PageDTO<ResumeLibDTO> p = resumeService.resumeLibList(resumeSearchDTO, page, size);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "简历详情，企业自主查询的简历，不是用户投递过来的简历", notes = "", tags = { "企业部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id") })
	@GetMapping("/resume/info")
	public ApiResponse resumeInfo(HttpServletRequest request, @RequestParam Long resumeId) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		ResumeDTO resumeDTO = resumeService.conmpanyGetResume(user.getId(), resumeId);

		return new ApiResponse(resumeDTO);
	}

	@ApiOperation(value = "下载单个简历", notes = "", tags = { "企业部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "long", name = "companyId", value = "公司id"),
			@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id"),
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "resumeName", value = "简历名称，懒得查库就直接前台传吧") })
	@GetMapping("/down/resume")
	public ApiResponse downloadResume(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long companyId, @RequestParam Long resumeId, @RequestParam String resumeName) {
		try {
			String pdfPath = resumeService.downloadResume(companyId, resumeId);
			InputStream is = new FileInputStream(pdfPath);
			OutputStream os = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(resumeName + ".pdf", "UTF-8"));
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len = 0;
			while ((len = is.read(b)) > 0) {
				os.write(b, 0, len);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ApiResponse();
	}

	@ApiOperation(value = "批量下载简历简历", notes = "", tags = { "企业部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "long", name = "companyId", value = "公司id"),
			@ApiImplicitParam(paramType = "query", dataType = "List", name = "resumeIds", value = "简历id数组") })
	@GetMapping("/down/resume/list")
	public ApiResponse downloadResumes(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long companyId, @RequestParam List<Long> resumeIds) {

		try {
			// 以流的形式下载文件。
			InputStream fis = resumeService.downloadResumes(companyId, resumeIds);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();

			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");

			// 如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("简历.rar", "UTF-8"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return new ApiResponse();
	}

	@ApiOperation(value = "充值", notes = "掉这个接口可以给微信下单，然后前端支付。完成支付", tags = { "企业部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "double", name = "amount", value = "充值金额") })
	@GetMapping("/pay")
	public ApiResponse deliveryPositionPay(HttpServletRequest request, @RequestParam Double amount) throws Exception {
		if (amount.doubleValue() <= 0.01) {
			throw new BizException("充值金额不能小于0.01元");
		}
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		String str = user.getId() + "_" + AccountTypeEnum.company.getId();
		redisUtil.set(str, amount, Constants.TEN_MINUTES);
		String ip = request.getRemoteAddr();
		Map<String, Object> resultMap = WeChatHttpUtil.unifiedOrder(user.getId(), AccountTypeEnum.company.getId(),
				amount, ip, null, Constants.TRADE_TYPE_NATIVE);
		String returnCode = (String) resultMap.get("return_code");// 通信标识
		String resultCode = (String) resultMap.get("result_code");// 交易标识
		logger.info("----返回参数：" + JSON.toJSONString(resultMap));
		// 只有当returnCode与resultCode均返回“success”，才代表微信支付统一下单成功
		if (Constants.RETURN_SUCCESS.equals(resultCode) && Constants.RETURN_SUCCESS.equals(returnCode)) {
			String urlCode = (String) resultMap.get("code_url");
			QrCide.createQrCode(str, urlCode);
		} else {
			throw new BizException("微信统一下单失败,失败原因:" + resultMap.get("err_code_des"));
		}
		return new ApiResponse("http://img.yizhihenhao.com/qr/" + str + ".png");
	}

	@ApiOperation(value = "检查公司是否付了钱", notes = "", tags = { "企业部分api" })
	@GetMapping("/is/pay")
	public ApiResponse isPay(HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		Double s = (Double) redisUtil.get(user.getId() + "_" + AccountTypeEnum.company.getId());
		if (s == null) {
			throw new BizException("支付超时");
		}
		if (s.doubleValue() == 0) {
			return new ApiResponse(true);
		}
		return new ApiResponse(false);
	}

}
