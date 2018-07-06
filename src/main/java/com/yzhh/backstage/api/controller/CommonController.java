package com.yzhh.backstage.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.VerifyCodeDTO;
import com.yzhh.backstage.api.util.RandomImageUtil;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommonController {
	
	@Autowired
	private RandomImageUtil randomImageUtil;

	@ApiOperation(value = "登录验证码", notes = "", tags = { "通用部分api" })
	@GetMapping("/login/verify/code")
	public ApiResponse getVerifyCodeImge() throws Exception {
		
		String number = randomImageUtil.getRandomNumber(4);
		String base64Image = randomImageUtil.createRandomNumberImage(number);
		VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO();
		verifyCodeDTO.setCode(number);
		verifyCodeDTO.setUrl(base64Image);
		
		return new ApiResponse(verifyCodeDTO);
	}

	@ApiOperation(value = "当前登录人信息", notes = "", tags = { "通用部分api" })
	@GetMapping("/login/info")
	public ApiResponse loginInfo(HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN);
		return new ApiResponse(user);
	}
	
	@ApiOperation(value = "获取短信验证码", notes = "", tags = { "通用部分api" })
	@GetMapping("/mobile/verify/code")
	public ApiResponse getPhoneVerificationCode(@RequestParam String phone) {
		
		
		
		return new ApiResponse("3233");
	}
	
	@ApiOperation(value = "获取邮箱验证码", notes = "", tags = { "通用部分api" })
	@GetMapping("/email/verify/code")
	public ApiResponse getEmailVerificationCode(@RequestParam String email) {
		
		
		
		return new ApiResponse("3233");
	}
}























