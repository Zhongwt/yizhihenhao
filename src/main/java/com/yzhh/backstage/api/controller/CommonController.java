package com.yzhh.backstage.api.controller;

import java.io.File;
import java.io.IOException;

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
import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.VerifyCodeDTO;
import com.yzhh.backstage.api.service.IAccountService;
import com.yzhh.backstage.api.service.IAttachmentService;
import com.yzhh.backstage.api.util.MoblieMessageUtil;
import com.yzhh.backstage.api.util.RedisUtil;
import com.yzhh.backstage.api.util.VerifyCodeUtils;
import com.yzhh.backstage.api.util.eamil.EmailUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommonController {
	
	@Autowired
	private VerifyCodeUtils verifyCodeUtils;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private IAccountService accountService;

	@ApiOperation(value = "登录验证码", notes = "", tags = { "通用部分api" })
	@GetMapping("/login/verify/code")
	public ApiResponse getVerifyCodeImge() throws Exception {
		
		String number = verifyCodeUtils.getRandomNumber(VerifyCodeUtils.LENGTH);
		String base64Image = verifyCodeUtils.createRandomNumberImage(number);
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
		String verificationCode = verifyCodeUtils.getRandomNumber(VerifyCodeUtils.LENGTH);
		MoblieMessageUtil.sendSms(phone, "{\"code\":\"" + verificationCode + "\"}", Constants.identifyingTempleteCode);
		redisUtil.set(Constants.phone_verification_code+phone, verificationCode,Constants.TEN_MINUTES);
		return new ApiResponse(verificationCode);
	}
	
	@ApiOperation(value = "获取邮箱验证码", notes = "", tags = { "通用部分api" })
	@GetMapping("/email/verify/code")
	public ApiResponse getEmailVerificationCode(@RequestParam String email) {
		
		String verificationCode = verifyCodeUtils.getRandomNumber(VerifyCodeUtils.LENGTH);
		EmailUtil.sendMail(email, "验证码", "您的验证码为："+verificationCode+"，请不要告诉任何人！");
		redisUtil.set(Constants.email_verification_code+email, verificationCode,Constants.TEN_MINUTES);
		
		return new ApiResponse(verificationCode);
	}
	
	@ApiOperation(value = "通用文件上传", notes = "", tags = { "通用部分api" })
	@PostMapping(value = "/upload/file")
    public ApiResponse updateStudentStatus(MultipartFile file,HttpServletRequest request) throws IOException {
		
		Long id = 0L;
		
        try {
           id = attachmentService.addAttachment(file.getOriginalFilename());
           String path="/home/yzhh/img/"+id;
           File newFile=new File(path);
           file.transferTo(newFile);
        } catch ( IOException e) {
            e.printStackTrace();
        }
		
		return new ApiResponse("http://img.yizhihenhao.com/"+id);
    }
	
	@ApiOperation(value = "通用获取配置金额", notes = "", tags = { "通用部分api" })
	@PostMapping(value = "/amount/setting")
    public ApiResponse getAmountSetting(@RequestParam String type) {
		Double amount = accountService.getAmountSettingByType(type);
		return new ApiResponse(amount);
    }
	
	@ApiOperation(value = "充值付款成功", notes = "由微信端调用，我们不用管", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "str", value = "充值信息，由 用户id+'_'+(用户类型，公司为1，用户为2)+'_'+充值金额",required=true)
		})
	@GetMapping("/pay/success")
	public ApiResponse deliveryPositionPaySuccess(String str) {

		String s = (String)redisUtil.get(str);
		if(s != null) {
			redisUtil.del(str);
			String[] strs = str.split("_");
			accountService.paySuccess(Long.parseLong(strs[0]), Integer.parseInt(strs[1]), Double.parseDouble(strs[2]));
		}else {
			throw new BizException("支付超时");
		}

		return new ApiResponse();
	}
	
	@ApiOperation(value = "充值付款失败，用户redis删除时间用的", notes = "", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "long", name = "str", value = "充值信息，由 用户id+'_'+(用户类型，公司为1，用户为2)+'_'+充值金额",required=true)
		})
	@GetMapping("/pay/fail")
	public ApiResponse deliveryPositionPayFail(String str) {

		String s = (String)redisUtil.get(str);
		if(s!= null) {
			redisUtil.del(str);
		}
		return new ApiResponse();
	}
	
}























