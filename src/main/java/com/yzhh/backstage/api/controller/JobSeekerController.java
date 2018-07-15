package com.yzhh.backstage.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.position.PositionDTO;
import com.yzhh.backstage.api.dto.position.SearchPositionDTO;
import com.yzhh.backstage.api.dto.wx.WeChatAppLoginReq;
import com.yzhh.backstage.api.dto.wx.WeChatPayDTO;
import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.service.IJobSeekerService;
import com.yzhh.backstage.api.service.IPositionService;
import com.yzhh.backstage.api.service.IWxService;

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
	//@Autowired
	//private IAccountService accountService;

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
	@PostMapping("/position/list")
	public ApiResponse positionList(@RequestBody SearchPositionDTO searchPositionDTO, Long page, Integer size) {

		searchPositionDTO.setStatus("招聘中");
		PageDTO<PositionDTO> p = positionService.list(searchPositionDTO, page, size);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "职位详情", notes = "", tags = { "求职者部分api" })
	@GetMapping("/position/info")
	public ApiResponse positionInfo(HttpServletRequest request, @RequestParam Long id) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		PositionDTO p = positionService.findById(id, user.getId());

		return new ApiResponse(p);
	}

	@ApiOperation(value = "公司详情", notes = "", tags = { "求职者部分api" })
	@GetMapping("/company/info")
	public ApiResponse companyInfo(@RequestParam Long id) {

		CompanyDTO companyDTO = companyService.findById(id);

		return new ApiResponse(companyDTO);
	}

	@ApiOperation(value = "收藏职位", notes = "", tags = { "求职者部分api" })
	@GetMapping("/collection/{positionId}")
	public ApiResponse collectionPosition(HttpServletRequest request, @PathVariable Long positionId) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		jobSeekerService.collectionPosition(positionId, user.getId());
		// CompanyDTO companyDTO = companyService.findById(id);

		return new ApiResponse();
	}

	@ApiOperation(value = "投递职位付费", notes = "掉这个接口可以给微信下单，然后前端支付。支付完成之后掉投递职位接口。完成投递", tags = { "求职者部分api" })
	@GetMapping("/delivery/pay/{positionId}")
	public ApiResponse deliveryPositionPay(HttpServletRequest request, @PathVariable Long positionId) throws Exception {

		WeChatPayDTO weChatPayDTO = null;
		// UserDTO user = (UserDTO)
		// request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		// Double amount =
		// accountService.getAmountSettingByType(Constants.delivery_resume_amout);
		//
		// String openId = user.getOpenId();
		// int totalFee = (int)(amount * 100);
		// String str = user.getId()+"_"+positionId+"_"+totalFee;
		// // 通过code获取网页授权access_token
		// // 构建微信统一下单需要的参数
		// Map<String, Object> map = new HashMap<>();
		// map.put("openId", openId);// 用户标识openId
		// map.put("remoteIp",request.getRemoteAddr());// 请求Ip地址
		// // 调用统一下单service
		// Map<String, Object> resultMap = WeChatHttpUtil.unifiedOrder(positionId,str,
		// totalFee, map);
		// String returnCode = (String) resultMap.get("return_code");// 通信标识
		// String resultCode = (String) resultMap.get("result_code");// 交易标识
		// // 只有当returnCode与resultCode均返回“success”，才代表微信支付统一下单成功
		// if (Constants.RETURN_SUCCESS.equals(resultCode) &&
		// Constants.RETURN_SUCCESS.equals(returnCode))
		// {
		// String appId = (String) resultMap.get("appid");// 微信公众号AppId
		// String timeStamp = WeChatHttpUtil.getTimeStamp();// 当前时间戳
		// String prepayId = "prepay_id=" + resultMap.get("prepay_id");// 统一下单返回的预支付id
		// String nonceStr = WeChatHttpUtil.getRandomStr(20);// 不长于32位的随机字符串
		// SortedMap<String, Object> signMap = new TreeMap<>();// 自然升序map
		// signMap.put("appId", appId);
		// signMap.put("package", prepayId);
		// signMap.put("timeStamp", timeStamp);
		// signMap.put("nonceStr", nonceStr);
		// signMap.put("signType", "MD5");
		//
		// String paySign = WeChatHttpUtil.getSign(signMap);// 获取签名
		//
		// weChatPayDTO = new WeChatPayDTO(appId, timeStamp, nonceStr, prepayId, openId,
		// paySign);
		// }
		// else
		// {
		// throw new BizException("微信统一下单失败,失败原因:" + resultMap.get("err_code_des"));
		// }
		return new ApiResponse(weChatPayDTO);
	}

	@ApiOperation(value = "投递职位", notes = "", tags = { "求职者部分api" })
	@GetMapping("/delivery/{positionId}")
	public ApiResponse deliveryPosition(HttpServletRequest request, @PathVariable Long positionId) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);

		jobSeekerService.deliveryPosition(user.getId(), positionId);

		return new ApiResponse();
	}

	@ApiOperation(value = "投递职位付款成功", notes = "由微信端调用，我们不用管", tags = { "求职者部分api" })
	@GetMapping("/delivery/position/pay/success")
	public ApiResponse deliveryPositionPaySuccess(String ids) {

		String[] idstr = ids.split("_");
		jobSeekerService.paySuccess(Long.parseLong(idstr[0]), Long.parseLong(idstr[1]), Integer.parseInt(idstr[2]));

		return new ApiResponse();
	}

}
