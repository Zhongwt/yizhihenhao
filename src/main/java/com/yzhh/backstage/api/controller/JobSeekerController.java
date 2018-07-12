package com.yzhh.backstage.api.controller;


import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.position.PositionDTO;
import com.yzhh.backstage.api.dto.position.SearchPositionDTO;
import com.yzhh.backstage.api.dto.wx.AccessToken;
import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.service.IJobSeekerService;
import com.yzhh.backstage.api.service.IPositionService;
import com.yzhh.backstage.api.service.IWxService;
import com.yzhh.backstage.api.util.WeChatHttpUtil;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api/job")
@CrossOrigin
public class JobSeekerController {

	@Autowired
	private IPositionService positionService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IJobSeekerService jobSeekerService;
	@Autowired
	private IWxService wxService;

	// 获取code
	@ApiOperation(value = "获取code", notes = "", tags = { "求职者部分api" })
	@GetMapping("/get/code")
	public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String param = request.getParameter("paramt");
		// System.out.println("-- "+param);
		// 微信ping的地址，来获取这个code，url是授权域名+方法名称
		String url = Constants.AUTHOR_MESSAGE_URL;
		String code_url = Constants.CODE_URL.replace("%", url);
		if (null == param) {
			response.sendRedirect(code_url);
		} else {
			url = url + "param1=" + URLEncoder.encode(param, "utf-8");
			code_url = Constants.CODE_URL.replace("%", url);
			// System.out.println(URLDecoder.decode(code_url, "UTF-8"));
			response.sendRedirect(code_url);
		}
	}

	// 微信服务 ping过来的方法，获取code及其openId 的数据
	@ApiOperation(value = "获取code和openId", notes = "", tags = { "求职者部分api" })
	@GetMapping("/get/code/token")
	public void getMessage(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String param = request.getParameter("param1");
		String openId = "";
		if (null != code) {
			openId = WeChatHttpUtil.getOauthOpenId(code); // 获取openid
		}
		AccessToken at = WeChatHttpUtil.getAccessToken(Constants.APPID, Constants.APPSECRET);
		WeChatUserInfo user = wxService.getUserInfoService(at.getToken(), openId);
		//WxUser wu = null;
		// map缓存 里面是否存在openid 存在 就不存储数据库，不存在 就 存储进数据库
		//if (!MapUtil.isExist(user.getOpenId())) {
			// 微信绑定user 入库操作
			//wu = userService2.addWeChatAndUser(user);
			//request.getSession().setAttribute("loginUser", wu);
		//} else {
			//wu = MapUtil.get(user.getOpenId());
			//request.getSession().setAttribute("loginUser", wu);
		//}
	}

	@ApiOperation(value = "职位列表", notes = "", tags = { "求职者部分api" })
	@PostMapping("/position/list")
	public ApiResponse positionList(@RequestBody SearchPositionDTO searchPositionDTO, Long page, Integer size) {

		searchPositionDTO.setStatus("招聘中");
		PageDTO<PositionDTO> p = positionService.list(searchPositionDTO, page, size);

		return new ApiResponse(p);
	}

	@ApiOperation(value = "职位详情", notes = "", tags = { "求职者部分api" })
	@GetMapping("/position/{id}")
	public ApiResponse positionInfo(HttpServletRequest request, @PathVariable Long id) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN);

		PositionDTO p = positionService.findById(id, user.getId());

		return new ApiResponse(p);
	}

	@ApiOperation(value = "公司详情", notes = "", tags = { "求职者部分api" })
	@GetMapping("/company/{id}")
	public ApiResponse companyInfo(@PathVariable Long id) {

		CompanyDTO companyDTO = companyService.findById(id);

		return new ApiResponse(companyDTO);
	}

	@ApiOperation(value = "收藏职位", notes = "", tags = { "求职者部分api" })
	@GetMapping("/collection/{positionId}")
	public ApiResponse collectionPosition(HttpServletRequest request, @PathVariable Long positionId) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN);

		jobSeekerService.collectionPosition(positionId, user.getId());
		// CompanyDTO companyDTO = companyService.findById(id);

		return new ApiResponse();
	}

	@ApiOperation(value = "投递职位付费", notes = "", tags = { "求职者部分api" })
	@GetMapping("/delivery/pay/{positionId}")
	public ApiResponse deliveryPositionPay(HttpServletRequest request, @PathVariable Long positionId) {

		return new ApiResponse();
	}

	@ApiOperation(value = "投递职位", notes = "", tags = { "求职者部分api" })
	@GetMapping("/delivery/{positionId}")
	public ApiResponse deliveryPosition(HttpServletRequest request, @PathVariable Long positionId) {

		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN);

		jobSeekerService.deliveryPosition(user.getId(), positionId);

		return new ApiResponse();
	}
}
