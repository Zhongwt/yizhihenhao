package com.yzhh.backstage.api.util.camera;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @description:敏感词语
 * @projectName:backstage-api
 * @className:SensitiveString.java
 * @author:wentao
 * @createTime:2018年8月17日 下午2:50:03
 * @version 1.0.1
 */
public class SensitiveString {
	
	@Autowired
	private WebRequest webRequest;

	private final String SENSITIVE_URL = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=";
	
	public boolean comfirmSensitiveString(String content) {
		
		if(StringUtils.isEmpty(content)) {
			return false;
		}
		
		String accessToken = webRequest.getAccessToken();
		
		String url = SENSITIVE_URL+accessToken;
		
		Map<String,Object> map = new HashMap<>();
		map.put("content", content);
		
		HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(map);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		
		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			String sessionData = responseEntity.getBody();
			System.out.println(sessionData);
			JSONObject jsonObj = JSON.parseObject(sessionData);
			if((Integer)jsonObj.get("errcode") == 0) {
				return false;
			}
		}
		return true;
	}
}
