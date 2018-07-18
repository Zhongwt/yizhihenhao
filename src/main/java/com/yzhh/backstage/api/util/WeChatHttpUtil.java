package com.yzhh.backstage.api.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.wx.AccessToken;
import com.yzhh.backstage.api.dto.wx.WxPaySendData;

public class WeChatHttpUtil {

	private static String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	 /**
     * 扩展xstream,使其支持name带有"_"的节点
     */
    public static XStream xStream = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_","_")));

	/**
	 * 
	 * @description:https请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param params
	 * @return
	 * @author:
	 * @createTime:2018年6月1日 下午2:20:45
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String params) {

		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();

		try {
			/** 创建SSLContext对象,并用我们制定的管理对象初始化 */
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			/** 从上述的SSLContext对象中获得 SSLSocketFactory */
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpsUrlConn = (HttpsURLConnection) url.openConnection();
			httpsUrlConn.setSSLSocketFactory(ssf);

			httpsUrlConn.setDoInput(true);
			httpsUrlConn.setDoOutput(true);
			httpsUrlConn.setUseCaches(false);
			httpsUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpsUrlConn.connect();
			}
			/** 当有数据提交时 */
			if (null != params) {
				OutputStream output = httpsUrlConn.getOutputStream();
				/** 设置编码格式，防止中文乱码 */
				output.write(params.getBytes("UTF-8"));
				output.close();
			}

			String contentType = httpsUrlConn.getHeaderField("Content-Type");
			if (contentType.indexOf("image") != -1) {// 返回值类型是图片
				String oldFileName = httpsUrlConn.getHeaderField("Content-disposition");
				String[] oldFileNameSplits = oldFileName.split("\\.");
				String fileType = oldFileNameSplits[oldFileNameSplits.length - 1];
				oldFileNameSplits = fileType.split("\\\"");
				fileType = oldFileNameSplits[0];
				String folderPath = getProjectPath() + "/resource/weixinimg";
				String fileName = "WX_" + getUUID() + "." + fileType;

				File folder = new File(folderPath);
				// 判断文件目录是否存在,创建文件夹和文件
				if (!folder.exists()) {
					folder.mkdirs();
				}
				File file = new File(folderPath + "/" + fileName);
				BufferedInputStream bis = new BufferedInputStream(httpsUrlConn.getInputStream());
				FileOutputStream fos = new FileOutputStream(file);
				byte[] bufferArray = new byte[1024];
				int len;
				while ((len = bis.read(bufferArray)) != -1) {
					fos.write(bufferArray, 0, len);
				}
				fos.close();
				bis.close();

				httpsUrlConn.disconnect();

				jsonObject = new JSONObject();
				jsonObject.put("JSON_PARAM_NAME_IMAGE_PATH", "/resource/weixinimg/" + fileName);
			} else {// 返回值类型是文本
				/** 将返回的输入流转换成字符 */
				InputStream inputStream = httpsUrlConn.getInputStream();
				InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(isr);

				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				bufferedReader.close();
				isr.close();
				inputStream.close();

				inputStream = null;
				httpsUrlConn.disconnect();
				jsonObject = JSONObject.parseObject(buffer.toString());

				/*
				 * String errcode = jsonObject.getString("JSON_PARAM_NAME_ERRCODE"); if(errcode
				 * != null && !"".equals(errcode) && !"0".equals(errcode)){
				 * logger.error(jsonObject.toJSONString());
				 * logger.error(WeixinConst.ERR_MSG_WEIXIN_RESPONSE.get(errcode)); throw new
				 * SystemException(WeixinConst.ERR_MSG_WEIXIN_INVOKE); }
				 */
			}
		} catch (ConnectException ce) {
			System.out.println("wechat connect is error." + ce);
			// logger.error("wechat connect is error.", ce);
		} catch (Exception e) {
			// logger.error("https request error.", e);
			System.out.println("https request error." + e);
		}
		return jsonObject;
	}

	/**
	 * 
	 * @description:
	 * @return
	 * @author:王涛
	 * @createTime:2018年6月1日 上午11:47:02
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	/**
	 * 
	 * @description:
	 * @return
	 * @author:王涛
	 * @createTime:2018年6月1日 上午11:45:58
	 */
	public final static String getProjectPath() {
		String ProjectPath = WeChatHttpUtil.class.getClassLoader().getResource("").toString();
		ProjectPath = replaceSlash(ProjectPath);
		int i = 0;
		while (ProjectPath.indexOf("//") != -1 && i < 20) {
			ProjectPath = ProjectPath.replaceAll("\\Q//\\E", "/");
			i++;
		}
		ProjectPath = ProjectPath.replaceAll("\\Qfile:\\E", "");
		if (ProjectPath.indexOf(":") != -1)
			ProjectPath = ProjectPath.substring(1);
		if (ProjectPath.indexOf("WEB-INF") != -1)
			ProjectPath = ProjectPath.substring(0, ProjectPath.indexOf("WEB-INF") - 1);
		return ProjectPath;
	}

	/**
	 * 
	 * @description:
	 * @param str
	 * @return
	 * @author:王涛
	 * @createTime:2018年6月1日 上午11:46:14
	 */
	public static String replaceSlash(String str) {
		String temp = "";
		int n = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '\\') {
				temp = temp + str.substring(n, i) + "/";
				n = i + 1;
			}
		}
		if (n < str.length())
			temp = temp + str.substring(n);
		return temp;
	}

	/**
	 * 获取openid
	 * 
	 * @param code
	 * @return
	 */
	public static String getOauthOpenId(String code) {
		StringBuilder url = new StringBuilder(ACCESS_TOKEN);
		url.append("?appid=").append(Constants.APPID).append("&secret=").append(Constants.APPSECRET).append("&code=")
				.append(code).append("&grant_type=").append(Constants.GRANTTYPE);
		JSONObject response = WeChatHttpUtil.httpsRequest(url.toString(), Constants.REQUEST_TYPE, null);
		String openId = response.getString(Constants.OPENIDVALUE);
		return openId;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = ACCESS_TOKEN.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
			}
		}
		return accessToken;
	}

	/**
	 * 
	 * @description:http 请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param params
	 * @return
	 * @author:王涛
	 * @createTime:2018年6月1日 下午2:20:04
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String params) {

		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();

		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setDoInput(true);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			/** 当有数据提交时 */
			if (null != params) {
				OutputStream output = httpUrlConn.getOutputStream();
				/** 设置编码格式，防止中文乱码 */
				output.write(params.getBytes("UTF-8"));
				output.close();
			}

			String contentType = httpUrlConn.getHeaderField("Content-Type");
			if (contentType.indexOf("image") != -1) {// 返回值类型是图片
				String oldFileName = httpUrlConn.getHeaderField("Content-disposition");
				String[] oldFileNameSplits = oldFileName.split("\\.");
				String fileType = oldFileNameSplits[oldFileNameSplits.length - 1];
				oldFileNameSplits = fileType.split("\\\"");
				fileType = oldFileNameSplits[0];
				String folderPath = getProjectPath() + "upload/images";
				String fileName = "WX_" + getUUID() + "." + fileType;

				File folder = new File(folderPath);
				// 判断文件目录是否存在,创建文件夹和文件
				if (!folder.exists()) {
					folder.mkdirs();
				}
				File file = new File(folderPath + "/" + fileName);
				BufferedInputStream bis = new BufferedInputStream(httpUrlConn.getInputStream());
				FileOutputStream fos = new FileOutputStream(file);
				byte[] bufferArray = new byte[1024];
				int len;
				while ((len = bis.read(bufferArray)) != -1) {
					fos.write(bufferArray, 0, len);
				}
				fos.close();
				bis.close();

				httpUrlConn.disconnect();

				jsonObject = new JSONObject();
				jsonObject.put("JSON_PARAM_NAME_IMAGE_PATH", "/upload/images/" + fileName);
			} else {// 返回值类型是文本
				/** 将返回的输入流转换成字符 */
				InputStream inputStream = httpUrlConn.getInputStream();
				InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(isr);

				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				bufferedReader.close();
				isr.close();
				inputStream.close();

				inputStream = null;
				httpUrlConn.disconnect();
				jsonObject = JSONObject.parseObject(buffer.toString());

			}
		} catch (ConnectException ce) {
		} catch (Exception e) {
		}
		return jsonObject;
	}

	/**
	 * 微信支付统一下单
	 * 
	 * @throws Exception
	 **/
	public static Map<String, Object> unifiedOrder(Long userId,String userName, String str, Double fee, Map<String, Object> map)
			throws Exception {
		Map<String, Object> resultMap;
		try {
			WxPaySendData paySendData = new WxPaySendData();
			String orderStr = "用户【"+userId+"】【"+userName+"】充值"+fee+"元";
			//String userStr = userId+"_"+userName;
			int totalFee = (int) (fee * 100);
			// 构建微信支付请求参数集合
			paySendData.setAppId(Constants.APPID);
			paySendData.setAttach(new String(str.getBytes("UTF-8")));
			paySendData.setBody(new String("充值描述".getBytes("UTF-8")));
			paySendData.setMchId(Constants.MCH_ID);
			paySendData.setNonceStr(getRandomStr(32));
			paySendData.setNotifyUrl(Constants.NOTIFY_URL + str);
			paySendData.setDeviceInfo("WEB");
			paySendData.setOutTradeNo(orderStr);
			paySendData.setTotalFee(totalFee);
			paySendData.setTradeType(Constants.TRADE_TYPE_JSAPI);
			paySendData.setSpBillCreateIp((String) map.get("remoteIp"));
			paySendData.setOpenId((String) map.get("openId"));
			// 将参数拼成map,生产签名
			SortedMap<String, Object> params = buildParamMap(paySendData);
			paySendData.setSign(WeChatHttpUtil.getSign(params));
			// 将请求参数对象转换成xml
			String reqXml = sendDataToXml(paySendData);
			// 发送请求
			byte[] xmlData = reqXml.getBytes();
			URL url = new URL(Constants.UNIFIED_ORDER_URL);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content_Type", "text/xml");
			urlConnection.setRequestProperty("Content-length", String.valueOf(xmlData.length));
			DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
			outputStream.write(xmlData);
			outputStream.flush();
			outputStream.close();
			resultMap = parseXml(urlConnection.getInputStream());
		} catch (Exception e) {
			throw new Exception("微信支付统一下单异常", e);
		}
		return resultMap;
	}
	
    /**
     *  获取当前时间戳
     * @return 当前时间戳字符串
     */
    public static String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis()/1000);
    }
	
	  /**
     * 请求参数转换成xml
     * @param data
     * @return xml字符串
     */
    public static String sendDataToXml(WxPaySendData data){
        xStream.autodetectAnnotations(true);
        xStream.alias("xml", WxPaySendData.class);
        return xStream.toXML(data);
    }

	/**
	 * 获取指定长度的随机字符串
	 * 
	 * @param length
	 * @return 随机字符串
	 */
	public static String getRandomStr(int length) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 构建统一下单参数map 用于生成签名
	 * @param data
	 * @return SortedMap<String,Object>
	 */
	private static SortedMap<String, Object> buildParamMap(WxPaySendData data)
	{
		SortedMap<String, Object> paramters = new TreeMap<String, Object>();
		if (null != data)
		{
			if (!StringUtils.isEmpty(data.getAppId()))
			{
				paramters.put("appid", data.getAppId());
			}
			if (!StringUtils.isEmpty(data.getAttach()))
			{
				paramters.put("attach", data.getAttach());
			}
			if (!StringUtils.isEmpty(data.getBody()))
			{
				paramters.put("body", data.getBody());
			}
			if (!StringUtils.isEmpty(data.getDetail()))
			{
				paramters.put("detail", data.getDetail());
			}
			if (!StringUtils.isEmpty(data.getDeviceInfo()))
			{
				paramters.put("device_info", data.getDeviceInfo());
			}
			if (!StringUtils.isEmpty(data.getFeeType()))
			{
				paramters.put("fee_type", data.getFeeType());
			}
			if (!StringUtils.isEmpty(data.getGoodsTag()))
			{
				paramters.put("goods_tag", data.getGoodsTag());
			}
			if (!StringUtils.isEmpty(data.getLimitPay()))
			{
				paramters.put("limit_pay", data.getLimitPay());
			}
			if (!StringUtils.isEmpty(data.getMchId()))
			{
				paramters.put("mch_id", data.getMchId());
			}
			if (!StringUtils.isEmpty(data.getNonceStr()))
			{
				paramters.put("nonce_str", data.getNonceStr());
			}
			if (!StringUtils.isEmpty(data.getNotifyUrl()))
			{
				paramters.put("notify_url", data.getNotifyUrl());
			}
			if (!StringUtils.isEmpty(data.getOpenId()))
			{
				paramters.put("openid", data.getOpenId());
			}
			if (!StringUtils.isEmpty(data.getOutTradeNo()))
			{
				paramters.put("out_trade_no", data.getOutTradeNo());
			}
			if (!StringUtils.isEmpty(data.getSign()))
			{
				paramters.put("sign", data.getSign());
			}
			if (!StringUtils.isEmpty(data.getSpBillCreateIp()))
			{
				paramters.put("spbill_create_ip", data.getSpBillCreateIp());
			}
			if (!StringUtils.isEmpty(data.getTimeStart()))
			{
				paramters.put("time_start", data.getTimeStart());
			}
			if (!StringUtils.isEmpty(data.getTimeExpire()))
			{
				paramters.put("time_expire", data.getTimeExpire());
			}
			if (!StringUtils.isEmpty(data.getProductId()))
			{
				paramters.put("product_id", data.getProductId());
			}
			if (data.getTotalFee() > 0)
			{
				paramters.put("total_fee", data.getTotalFee());
			}
			if (!StringUtils.isEmpty(data.getTradeType()))
			{
				paramters.put("trade_type", data.getTradeType());
			}
			// 申请退款参数
			if (!StringUtils.isEmpty(data.getTransactionId()))
			{
				paramters.put("transaction_id", data.getTransactionId());
			}
			if (!StringUtils.isEmpty(data.getOutRefundNo()))
			{
				paramters.put("out_refund_no", data.getOutRefundNo());
			}
			if (!StringUtils.isEmpty(data.getOpUserId()))
			{
				paramters.put("op_user_id", data.getOpUserId());
			}
			if (!StringUtils.isEmpty(data.getRefundFeeType()))
			{
				paramters.put("refund_fee_type", data.getRefundFeeType());
			}
			if (null != data.getRefundFee() && data.getRefundFee() > 0)
			{
				paramters.put("refund_fee", data.getRefundFee());
			}
		}
		return paramters;
	}
	
	   /**
     * 获取微信签名
     * @param map 请求参数集合
     * @return 微信请求签名串
     */
    @SuppressWarnings("rawtypes")
	public static String getSign(SortedMap<String,Object> map){
        StringBuffer sb = new StringBuffer();
		Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //参数中sign、key不参与签名加密
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)){
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + Constants.KEY);
        String sign = MD5.MD5Encode(sb.toString(),null).toUpperCase();
        return sign;
    }
    
    /**
     * 解析微信服务器发来的请求
     * @param inputStream
     * @return 微信返回的参数集合
     * @throws Exception 
     */
    public static SortedMap<String,Object> parseXml(InputStream inputStream) throws Exception {
        SortedMap<String,Object> map = new TreeMap<>();
        try {
            //获取request输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            //得到xml根元素
            Element root = document.getRootElement();
            //得到根元素所有节点
            List<Element> elementList = root.elements();
            //遍历所有子节点
            for (Element element:elementList){
                map.put(element.getName(),element.getText());
            }
            //释放资源
            inputStream.close();
        } catch (Exception e) {
            throw new Exception("微信工具类:解析xml异常",e);
        }
        return map;
    }
}
