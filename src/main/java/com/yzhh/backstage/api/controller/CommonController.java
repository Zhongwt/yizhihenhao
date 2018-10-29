package com.yzhh.backstage.api.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.TypeDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.VerifyCodeDTO;
import com.yzhh.backstage.api.service.IAccountService;
import com.yzhh.backstage.api.service.IAttachmentService;
import com.yzhh.backstage.api.service.IBannerService;
import com.yzhh.backstage.api.service.IPositionService;
import com.yzhh.backstage.api.util.MoblieMessageUtil;
import com.yzhh.backstage.api.util.RedisUtil;
import com.yzhh.backstage.api.util.VerifyCodeUtils;
import com.yzhh.backstage.api.util.WeChatHttpUtil;
import com.yzhh.backstage.api.util.eamil.EmailUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommonController {
	
	private Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private VerifyCodeUtils verifyCodeUtils;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private IAccountService accountService;
	@SuppressWarnings("unused")
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IBannerService bannerService;

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
    public ApiResponse getAmountSetting(@RequestBody TypeDTO nameDTO) {
		Double amount = accountService.getAmountSettingByType(nameDTO.getType());
		return new ApiResponse(amount);
    }
	
	@ApiOperation(value = "充值付款成功", notes = "由微信端调用，我们不用管", tags = { "求职者部分api" })
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "String", name = "str", value = "充值信息，由 用户id+'_'+(用户类型，公司为1，用户为2)+'_'+充值金额",required=true)
		})
	@GetMapping("/pay/success")
	public ApiResponse deliveryPositionPaySuccess(String str) {

		String[] strs = str.split("_");
		Long relationId = Long.parseLong(strs[0]);
		Integer type = Integer.parseInt(strs[1]);
		Double totalFee = Double.parseDouble(strs[2]);
		
		Double s = (Double)redisUtil.get(relationId+"_"+type);
		logger.info("支付成功字符串:"+str);
		if(s != null) {
			if(s.doubleValue() == 0) {
				throw new BizException("支付已完成");
			}else if(s.doubleValue() != totalFee.doubleValue()){
				throw new BizException("支付金额不对");
			}else {
				redisUtil.set(relationId+"_"+type, 0D,Constants.TEN_MINUTES);
				accountService.paySuccess(relationId, type, totalFee);
			}
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
		str = str.substring(0, str.lastIndexOf("_"));
		String s = (String)redisUtil.get(str);
		if(s!= null) {
			redisUtil.del(str);
		}
		return new ApiResponse();
	}
	
	@ApiOperation(value = "充值付款成功", notes = "由微信端调用，我们不用管", tags = { "求职者部分api" })
	@RequestMapping("/wx/pay/success")
	public ApiResponse paySuccess(HttpServletRequest request) throws Exception {

        //设置支付参数
		InputStream inputStream;
        inputStream = request.getInputStream();
        Map<String, Object> resultMap = WeChatHttpUtil.parseXml(inputStream);
		
		logger.info("微信回调参数："+JSON.toJSONString(resultMap));
		
		String str = (String)resultMap.get("attach");
		
		String[] strs = str.split("_");
		Long relationId = Long.parseLong(strs[0]);
		Integer type = Integer.parseInt(strs[1]);
		Double totalFee = Double.parseDouble(strs[2]);
		
		Double s = (Double)redisUtil.get(relationId+"_"+type);
		logger.info("支付成功字符串:"+str);
		if(s != null) {
			if(s.doubleValue() == 0) {
				throw new BizException("支付已完成");
			}else if(s.doubleValue() != totalFee.doubleValue()){
				throw new BizException("支付金额不对");
			}else {
				redisUtil.set(relationId+"_"+type, 0D,Constants.TEN_MINUTES);
				accountService.paySuccess(relationId, type, totalFee);
			}
		}else {
			throw new BizException("支付超时");
		}

		return new ApiResponse();
	}
	
	public String reciverWx(HttpServletRequest request) throws IOException
    {
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null)
        {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        return sb.toString();
    }

	@ApiOperation(value = "充值赠送金额", notes = "", tags = { "通用部分api" })
	@ApiImplicitParams({ 
		})
	@GetMapping("/amount/gift")
	public ApiResponse amountGift() {
		return new ApiResponse(accountService.getGift());
	}
	
	@ApiOperation(value = "微信banner", notes = "", tags = { "通用部分api" })
	@ApiImplicitParams({ 
		})
	@GetMapping("/wx/banner")
	public ApiResponse bannerList() {
		return new ApiResponse(bannerService.list());
	}

	
}























