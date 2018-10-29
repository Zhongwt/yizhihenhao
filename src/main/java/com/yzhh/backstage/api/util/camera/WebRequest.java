package com.yzhh.backstage.api.util.camera;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.stream.FileImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.position.PositionDTO;

public class WebRequest {

	private Logger logger = LoggerFactory.getLogger(WebRequest.class);
	//小程序码
	private String WX_CODE = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
	//accesss_token
	private String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	//获取access_token用于获取小程序码
	public  String getAccessToken() {
		String url = ACCESS_TOKEN +"&appid="+Constants.APPID + "&secret="+ Constants.APPSECRET;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			String sessionData = responseEntity.getBody();
			logger.info(sessionData);
			JSONObject jsonObj = JSON.parseObject(sessionData);
			return jsonObj.getString("access_token");
		}
		throw new BizException("获取access_token错误");
	}
	
	public String getWxCode(String path,String str) {
		
		String accessToken = this.getAccessToken();
		
		String url = WX_CODE + accessToken;
		
		Map<String,Integer> colorMap = new HashMap<>();
		colorMap.put("r", 0);
		colorMap.put("g", 0);
		colorMap.put("b", 0);
		
		Map<String,Object> map = new HashMap<>();
		map.put("scene", str);
		map.put("page", path);
		map.put("width", 430);
		map.put("auto_color", false);
		map.put("line_color", colorMap);
		map.put("is_hyaline", true);
		
		HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(map);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, byte[].class);
		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			byte[] sessionData = responseEntity.getBody();
			
			String filePath = "/home/yzhh/img/"+str+"_spCode.png";
		    try {
		    	@SuppressWarnings("resource")
				FileImageOutputStream imageOutput = new FileImageOutputStream(new File(filePath));//打开输入流
				imageOutput.write(sessionData, 0, sessionData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "http://img.yizhihenhao.com/"+str+"_spCode.png";
		}
		throw new BizException("获取小程序码错误");
	}
	public String getCamera(PositionDTO positionDTO) {
		
		String path="pages/positionDetail/main";
		String str = positionDTO.getId()+"";
		String charset = "utf-8";
		
		//获取小程序码
		String spCode = this.getWxCode(path, str);		
		positionDTO.setSpCode(spCode);
		positionDTO.setDescription(null);
		
		logger.info("发送post请求");
		String param = "data="+JSON.toJSONString(positionDTO);
		System.out.println("请求参数："+param);
		String url = "http://132.232.10.110:8080/positionDetail";
		
		OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn =  (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            conn.setConnectTimeout(2000);
            
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), charset);
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return "http://132.232.10.110:8080"+result;
    }    
	
}
