package com.yzhh.backstage.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.account.AccountDTO;
import com.yzhh.backstage.api.dto.account.AccountLogDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.jobseeker.DeliveryDTO;
import com.yzhh.backstage.api.dto.jobseeker.JobSeekerDTO;
import com.yzhh.backstage.api.dto.position.PositionCityDTO;
import com.yzhh.backstage.api.dto.position.PositionDTO;
import com.yzhh.backstage.api.dto.position.SearchPositionDTO;
import com.yzhh.backstage.api.dto.resume.EducationalBackgroundDTO;
import com.yzhh.backstage.api.dto.resume.InternshipExpectationDTO;
import com.yzhh.backstage.api.dto.resume.InternshipExperienceDTO;
import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.dto.resume.ProjectExperienceDTO;
import com.yzhh.backstage.api.dto.resume.ResumeDTO;
import com.yzhh.backstage.api.dto.resume.ResumeSearchDTO;
import com.yzhh.backstage.api.dto.resume.SelfEvaluationDTO;
import com.yzhh.backstage.api.dto.resume.SkillHobbyDTO;
import com.yzhh.backstage.api.dto.resume.WorksShowDTO;
import com.yzhh.backstage.api.dto.wx.WeChatAppLoginReq;
import com.yzhh.backstage.api.dto.wx.WeChatPayDTO;
import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;
import com.yzhh.backstage.api.enums.AccountTypeEnum;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.service.IAccountService;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.service.IJobSeekerService;
import com.yzhh.backstage.api.service.IPositionService;
import com.yzhh.backstage.api.service.IResumeService;
import com.yzhh.backstage.api.service.IWxService;
import com.yzhh.backstage.api.util.ValidateUtil;
import com.yzhh.backstage.api.util.WeChatHttpUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api/job")
public class JobSeekerController {

	@Autowired
	private IPositionService positionService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IJobSeekerService jobSeekerService;
	@Autowired
	private IWxService wxService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IResumeService resumeService;

	private Logger logger = LoggerFactory.getLogger(JobSeekerController.class);

	// 微信服务 ping过来的方法，获取code及其openId 的数据
	@ApiOperation(value = "获取code和openId", notes = "", tags = { "求职者部分api" })
	@PostMapping("/get/code/token")
	public ApiResponse getMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody WeChatAppLoginReq req) {

		UserDTO userDTO = null;
		// 微信的接口
		String url = Constants.WX_GET_OPENID.replace("{APPID}", Constants.APPID)
				.replace("{SECRET}", Constants.APPSECRET).replace("{code}", req.getCode());
		RestTemplate restTemplate = new RestTemplate();
		// 进行网络请求,访问url接口
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		// 根据返回值进行后续操作
		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			String sessionData = responseEntity.getBody();
			logger.info(sessionData);
			// 解析从微信服务器获得的openid和session_key;
			JSONObject jsonObj = JSON.parseObject(sessionData);
			// 获取用户的唯一标识
			String openId = jsonObj.getString("openid");
			logger.info(openId);
			// 获取会话秘钥
			String sessionKey = jsonObj.getString("session_key");
			logger.info(sessionKey);
			
			// 下面就可以写自己的业务代码了
			// 最后要返回一个自定义的登录态,用来做后续数据传输的验证
			WeChatUserInfo user = wxService.getUserInfoService(req,openId,sessionKey);
			userDTO = jobSeekerService.login(user);
		}

		return new ApiResponse(userDTO);
	}

	@ApiOperation(value = "职位列表", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "long", name = "page", value = "页码"),
		@ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "页面大小")})
	@PostMapping("/position/list")
	public ApiResponse positionList(@RequestBody SearchPositionDTO searchPositionDTO, Long page, Integer size) {

		searchPositionDTO.setStatus("招聘中");
		PageDTO<PositionDTO> p = positionService.list(searchPositionDTO, page, size);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "职位详情", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "long", name = "id", value = "职位id")})
	@GetMapping("/position/info")
	public ApiResponse positionInfo(HttpServletRequest request, @RequestParam Long id) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		PositionDTO p = positionService.findById(id, user == null ? null : user.getId());

		return new ApiResponse(p);
	}

	@ApiOperation(value = "公司详情", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "long", name = "id", value = "公司id")})
	@GetMapping("/company/info")
	public ApiResponse companyInfo(@RequestParam Long id) {

		CompanyDTO companyDTO = companyService.findById(id);

		return new ApiResponse(companyDTO);
	}
	
	@ApiOperation(value = "职位是否被收藏", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "long", name = "id", value = "公司id")})
	@GetMapping("/is/collection")
	public ApiResponse isCollection(HttpServletRequest request, @RequestParam Long positionId) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		
		return new ApiResponse(jobSeekerService.isCollectionPosition(positionId, user.getId()));
	}

	@ApiOperation(value = "收藏或取消收藏职位", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "path", dataType = "long", name = "positionId", value = "职位id")})
	@GetMapping("/collection/{positionId}")
	public ApiResponse collectionPosition(HttpServletRequest request, @PathVariable Long positionId) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		jobSeekerService.collectionPosition(positionId, user.getId());

		return new ApiResponse();
	}
	
	@ApiOperation(value = "充值", notes = "掉这个接口可以给微信下单，然后前端支付。完成支付", tags = { "求职者部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "double", name = "amount", value = "充值金额")})
	@GetMapping("/pay")
	public ApiResponse deliveryPositionPay(HttpServletRequest request,Double amount) throws Exception {

		WeChatPayDTO weChatPayDTO = null;
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		String openId = user.getOpenId();
		String str = user.getId()+"_"+user.getName()+"_"+AccountTypeEnum.job_seeker.getId()+"_"+amount;
		//String str = user.getId() + "_" + positionId + "_" + totalFee;
		// 通过code获取网页授权access_token
		// 构建微信统一下单需要的参数
		Map<String, Object> map = new HashMap<>();
		map.put("openId", openId);// 用户标识openId
		map.put("remoteIp", request.getRemoteAddr());// 请求Ip地址
		// 调用统一下单service
		Map<String, Object> resultMap = WeChatHttpUtil.unifiedOrder(user.getId(),user.getName(), str, amount, map);
		String returnCode = (String) resultMap.get("return_code");// 通信标识
		String resultCode = (String) resultMap.get("result_code");// 交易标识
		// 只有当returnCode与resultCode均返回“success”，才代表微信支付统一下单成功
		if (Constants.RETURN_SUCCESS.equals(resultCode) && Constants.RETURN_SUCCESS.equals(returnCode)) {
			String appId = (String) resultMap.get("appid");// 微信公众号AppId
			String timeStamp = WeChatHttpUtil.getTimeStamp();// 当前时间戳
			String prepayId = "prepay_id=" + resultMap.get("prepay_id");// 统一下单返回的预支付id
			String nonceStr = WeChatHttpUtil.getRandomStr(20);// 不长于32位的随机字符串
			SortedMap<String, Object> signMap = new TreeMap<>();// 自然升序map
			signMap.put("appId", appId);
			signMap.put("package", prepayId);
			signMap.put("timeStamp", timeStamp);
			signMap.put("nonceStr", nonceStr);
			signMap.put("signType", "MD5");

			String paySign = WeChatHttpUtil.getSign(signMap);// 获取签名

			weChatPayDTO = new WeChatPayDTO(appId, timeStamp, nonceStr, prepayId, openId, paySign);
		} else {
			throw new BizException("微信统一下单失败,失败原因:" + resultMap.get("err_code_des"));
		}
		return new ApiResponse(weChatPayDTO);
	}
	
	@ApiOperation(value = "检查投递职位匹配度", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "positionId", value = "职位id"),
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id"),
			})
	@GetMapping("/match/position")
	public ApiResponse deliveryPosition(@RequestParam Long positionId,@RequestParam Long resumeId) {

		String s = jobSeekerService.matchResumeAndPosition(resumeId, positionId);

		return new ApiResponse(s);
	}

	@ApiOperation(value = "投递职位", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "positionId", value = "职位id"),
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id"),
			})
	@GetMapping("/delivery/position")
	public ApiResponse deliveryPosition(HttpServletRequest request, @RequestParam Long positionId,@RequestParam Long resumeId) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		jobSeekerService.deliveryPosition(user.getId(), positionId,resumeId);

		return new ApiResponse();
	}
	
	@ApiOperation(value = "获取所有有职位的城市", notes = "", tags = { "求职者部分api" })
	@GetMapping("/position/city")
	public ApiResponse positionCity() {

		PositionCityDTO positionCityDTO = positionService.getPositionCity();

		return new ApiResponse(positionCityDTO);
	}
	
	@ApiOperation(value = "获取求职者用户信息", notes = "", tags = { "求职者部分api" })
	@GetMapping("/info")
	public ApiResponse jobSeekerInfo(HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		JobSeekerDTO jobSeekerDTO = jobSeekerService.findById(user.getId());

		return new ApiResponse(jobSeekerDTO);
	}
	
	@ApiOperation(value = "编辑个人信息", notes = "", tags = { "求职者部分api" })
	@PostMapping("/edit/info")
	public ApiResponse jobSeekerInfo(HttpServletRequest request,@RequestBody @Valid JobSeekerDTO jobSeekerDTO,BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		jobSeekerDTO.setId(user.getId());
		jobSeekerService.editInfo(jobSeekerDTO);

		return new ApiResponse();
	}
	
	@ApiOperation(value = "简历列表", notes = "", tags = { "求职者部分api" })
	@GetMapping("/resume/list")
	public ApiResponse resumeList(HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		resumeService.getJobSeekerResumePoorList(user.getId());
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "将简历设置成默认", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "path", dataType = "long", name = "resumeId", value = "简历id")})
	@PutMapping("/resume/default/{resumeId}")
	public ApiResponse setDefaultResume(@PathVariable Long resumeId) {

		resumeService.setResumeDefault(resumeId);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "删除简历", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "path", dataType = "long", name = "resumeId", value = "简历id")})
	@DeleteMapping("/resume/delete/{resumeId}")
	public ApiResponse deleteResume(@PathVariable Long resumeId) {

		resumeService.deleteResume(resumeId);;
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "我的收藏职位", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "long", name = "page", value = "页码"),
		@ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "页面大小")})
	@GetMapping("/position/list")
	public ApiResponse collectionPositionList(HttpServletRequest request,Long page, Integer size) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		PageDTO<PositionDTO> p = positionService.collectionList(user.getId(), page, size);

		return new ApiResponse(p);
	}
	
	@ApiOperation(value = "我的账户", notes = "", tags = { "求职者部分api" })
	@GetMapping("/account")
	public ApiResponse myAccount(HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		AccountDTO accountDTO = accountService.getAccount(user.getId(),AccountTypeEnum.job_seeker.getId());

		return new ApiResponse(accountDTO);
	}
	
	@ApiOperation(value = "我的账户流水，类型 非必填,全部的时候为空，支出就直接type=支出", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "String", name = "type", value = "流水类型，支出或收入，全部为空"),
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "page", value = "页码"),
		@ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "页面大小")
		})
	@GetMapping("/account/log")
	public ApiResponse myAccountLog(@RequestParam Long accountId,String type,Long page,Integer size) {

		PageDTO<AccountLogDTO> pageDTO = accountService.getAccountLogList(accountId,null, page, size);

		return new ApiResponse(pageDTO);
	}
	
	@ApiOperation(value = "投递支付记录", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "page", value = "页码"),
		@ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "页面大小")
		})
	@GetMapping("/delivery/log")
	public ApiResponse myDeliveryList(HttpServletRequest request,Long page,Integer size) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		PageDTO<DeliveryDTO> p = accountService.deliveryPayList(user.getId(), page, size);
		
		return new ApiResponse(p);
	}
	
	@ApiOperation(value = "简历详情", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id",required=true)
		})
	@GetMapping("/resume/info")
	public ApiResponse resumeInfo(@RequestParam Long resumeId) {

		ResumeDTO resumeDTO = resumeService.findById(resumeId);
		
		return new ApiResponse(resumeDTO);
	}
	
	@ApiOperation(value = "保存实习期望", notes = "通过是否有id来确定是新增还是修改", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id",required=true)
		})
	@PostMapping("/save/internship/expectation")
	public ApiResponse saveInternshipExpectation(@RequestParam Long resumeId,@RequestBody @Valid InternshipExpectationDTO internshipExpectationDTO,BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		resumeService.saveInternshipExpectation(resumeId, internshipExpectationDTO);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "保存实习经历", notes = "通过是否有id来确定是新增还是修改", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id",required=true)
		})
	@PostMapping("/save/internship/experience")
	public ApiResponse saveInternshipExperience(@RequestParam Long resumeId,@RequestBody @Valid InternshipExperienceDTO internshipExperienceDTO,BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		resumeService.saveInternshipExperience(resumeId, internshipExperienceDTO);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "保存教育背景", notes = "通过是否有id来确定是新增还是修改", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id",required=true)
		})
	@PostMapping("/save/education/background")
	public ApiResponse saveEducationalBackground(@RequestParam Long resumeId,@RequestBody @Valid EducationalBackgroundDTO educationalBackgroundDTO,BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		resumeService.saveEducationalBackground(resumeId, educationalBackgroundDTO);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "保存项目经历", notes = "通过是否有id来确定是新增还是修改", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id",required=true)
		})
	@PostMapping("/save/project/experience")
	public ApiResponse saveProjectExperience(@RequestParam Long resumeId,@RequestBody @Valid ProjectExperienceDTO projectExperienceDTO,BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		resumeService.saveProjectExperience(resumeId, projectExperienceDTO);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "保存爱好和能力", notes = "通过是否有id来确定是新增还是修改", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id",required=true)
		})
	@PostMapping("/save/skill/hobby")
	public ApiResponse saveSkillHobby(@RequestParam Long resumeId,@RequestBody @Valid SkillHobbyDTO skillHobbyDTO,BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		resumeService.saveSkillHobby(resumeId, skillHobbyDTO);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "保存作品展示", notes = "通过是否有id来确定是新增还是修改", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id",required=true)
		})
	@PostMapping("/save/works/show")
	public ApiResponse saveWorksShow(@RequestParam Long resumeId,@RequestBody @Valid WorksShowDTO worksShowDTO,BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		resumeService.saveWorksShow(resumeId, worksShowDTO);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "保存作品展示", notes = "通过是否有id来确定是新增还是修改", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id",required=true)
		})
	@PostMapping("/save/self/evaluation")
	public ApiResponse saveSelfEvaluation(@RequestParam Long resumeId,@RequestBody @Valid SelfEvaluationDTO selfEvaluationDTO,BindingResult result) {

		if (result.hasErrors()) {
			ValidateUtil.throwBeanValidationException(result, CommonError.REQUEST_PARAMETER_ERROR.getId());
		}
		
		resumeService.saveSelfEvaluation(resumeId, selfEvaluationDTO);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "新建简历", notes = "", tags = { "求职者部分api" })
	
	@PostMapping("/add/resume")
	public ApiResponse addResume(HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		
		resumeService.add(user.getId());
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "修改简历名称", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "resumeId", value = "简历id",required=true),
		@ApiImplicitParam(paramType = "query", dataType = "String", name = "name", value = "简历名称",required=true)
		})
	@PostMapping("/modify/resume/name")
	public ApiResponse modifyResumeName(@RequestParam Long resumeId,@RequestParam String name) {

		resumeService.modifyResumeName(resumeId, name);
		
		return new ApiResponse();
	}
	
	@ApiOperation(value = "投递列表", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "page", value = "页数"),
		@ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "页面大小")
		})
	@PostMapping("/delivery/resume/list")
	public ApiResponse deliveryResumeList(HttpServletRequest request, @RequestBody ResumeSearchDTO resumeSearchDTO, Long page,
			Integer size) {

		if (resumeSearchDTO == null) {
			resumeSearchDTO = new ResumeSearchDTO();
		}
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		resumeSearchDTO.setJobSeekerId(user.getId());
		PageDTO<PageResumeDTO> p = resumeService.queryPage(resumeSearchDTO, page, size);
		
		return new ApiResponse(p);
	}
	
	@ApiOperation(value = "接受面试", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "deliveryResumeId", value = "投递id",required=true)
		})
	@GetMapping("/accept/Interview")
	public ApiResponse acceptInterview(@RequestParam Long deliveryResumeId) {

		resumeService.acceptInterview(deliveryResumeId);
		
		return new ApiResponse();
	}

}































