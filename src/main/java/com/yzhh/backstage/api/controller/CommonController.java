package com.yzhh.backstage.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.VerifyCodeDTO;
import com.yzhh.backstage.api.service.IAccountService;
import com.yzhh.backstage.api.service.IAttachmentService;
import com.yzhh.backstage.api.util.MoblieMessageUtil;
import com.yzhh.backstage.api.util.RandomImageUtil;
import com.yzhh.backstage.api.util.RedisUtil;
import com.yzhh.backstage.api.util.VerificationCodeProvider;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommonController {
	
	@Autowired
	private RandomImageUtil randomImageUtil;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private IAccountService accountService;

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
		UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION);
		return new ApiResponse(user);
	}
	
	@ApiOperation(value = "获取短信验证码", notes = "", tags = { "通用部分api" })
	@GetMapping("/mobile/verify/code")
	public ApiResponse getPhoneVerificationCode(@RequestParam String phone) {
		String verificationCode = VerificationCodeProvider.getVerificationCode(VerificationCodeProvider.LENGTH);
		MoblieMessageUtil.sendSms(phone, "{\"code\":\"" + verificationCode + "\"}", Constants.identifyingTempleteCode);
		redisUtil.set(Constants.phone_verification_code+phone, verificationCode,Constants.TEN_MINUTES);
		return new ApiResponse(verificationCode);
	}
	
	@ApiOperation(value = "获取邮箱验证码", notes = "", tags = { "通用部分api" })
	@GetMapping("/email/verify/code")
	public ApiResponse getEmailVerificationCode(@RequestParam String email) {
		
		String verificationCode = VerificationCodeProvider.getVerificationCode(VerificationCodeProvider.LENGTH);
		redisUtil.set(Constants.email_verification_code+email, verificationCode,Constants.TEN_MINUTES);
		
		return new ApiResponse(verificationCode);
	}
	
	@ApiOperation(value = "通用文件上传", notes = "", tags = { "通用部分api" })
	@PostMapping(value = "/upload/file")
    public ApiResponse updateStudentStatus(MultipartFile file,HttpServletRequest request) {
		
		Long id = 0L;
        try {
           id = attachmentService.addAttachment(file.getOriginalFilename());
           String path="/home/yzhh/file/"+id;
           File newFile=new File(path);
           file.transferTo(newFile);
        } catch ( IOException e) {
            e.printStackTrace();
        }
		
		return new ApiResponse(id);
    }
	
	@ApiOperation(value = "通用获取配置金额", notes = "", tags = { "通用部分api" })
	@PostMapping(value = "/amount/setting")
    public ApiResponse getAmountSetting(@RequestParam String type) {
		Double amount = accountService.getAmountSettingByType(type);
		return new ApiResponse(amount);
    }
	
	@ApiOperation(value = "充值付款成功", notes = "由微信端调用，我们不用管", tags = { "求职者部分api" })
	@GetMapping("/pay/success")
	public ApiResponse deliveryPositionPaySuccess(String str) {

		String[] strs = str.split("_");
		accountService.paySuccess(Long.parseLong(strs[0]), strs[1], Integer.parseInt(strs[2]), Double.parseDouble(strs[3]));

		return new ApiResponse();
	}
	
	public static void main(String[] args) {
		System.out.println(new Date(1632258691318L));
	}
}























