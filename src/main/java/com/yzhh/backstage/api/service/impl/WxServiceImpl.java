package com.yzhh.backstage.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.base64url.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.dto.wx.WeChatAppLoginReq;
import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;
import com.yzhh.backstage.api.service.IWxService;
import com.yzhh.backstage.api.util.HmacUtil;

@Service
public class WxServiceImpl implements IWxService {

	private Logger logger = LoggerFactory.getLogger(WxServiceImpl.class);
	
	public static boolean initialized = false;

	@Override
	public WeChatUserInfo getUserInfoService(WeChatAppLoginReq req, String openId, String sessionKey) {

		WeChatUserInfo user = null;
		
		String wx_signature = HmacUtil.SHA1(req.getRawData() + sessionKey);
		if (!wx_signature.equals(req.getSignature())) {
			logger.info(" req signature=" + wx_signature);
			logger.info(" java signature=" + req.getSignature());
			throw new BizException("微信获取个人信息签名错误");
		}

		byte[] resultByte = null;
		try {
			resultByte = decrypt(Base64.decode(req.getEncryptedData()), Base64.decode(sessionKey),
					Base64.decode(req.getIv()));
		} catch (Exception e) {
			throw new BizException("微信获取个人信息失败");
		}
		if (null != resultByte && resultByte.length > 0) {
			String userInfoStr = "";
			try {
				userInfoStr = new String(resultByte, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
			logger.info("userInfo = " + userInfoStr);
			
			user = (WeChatUserInfo)JSON.parse(userInfoStr);
		}

		return user;
	}

	private byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
		initialize();
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			Key sKeySpec = new SecretKeySpec(keyByte, "AES");

			cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void initialize() {
		if (initialized)
			return;
		Security.addProvider(new BouncyCastleProvider());
		initialized = true;
	}

	// 生成iv
	public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
		AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
		params.init(new IvParameterSpec(iv));
		return params;
	}
}
