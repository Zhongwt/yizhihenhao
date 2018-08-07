package com.yzhh.backstage.api.constans;

/**
 * @description:redis中的缓存key，的字符串拼接
 * @projectName:common-redis
 * @className:Constants.java
 * @author:衷文涛
 * @createTime:2018年3月9日 下午2:03:01
 * @version 1.0
 */
public class Constants
{
	public final static String TOKEN = "token";
	public final static String IDENTITY = "identity";
	
	public final static String USER_LOGIN_SESSION = "user_login_session";
	
	public final static String ADMIN_LOGIN = "admin_login_";
	
	public final static String COMPANY_LOGIN = "company_login_";
	
	public final static String JOB_SEEKER_LOGIN = "job_seeker_login_";
	
	public final static String IP = "ip";
	
	public final static String phone_verification_code = "phone_verification_code_";
	
	public final static String email_verification_code = "email_verification_code_";
	
	public final static int TEN_MINUTES = 60 * 10;		//10分钟
	
	public final static int HALF_HOUR = 60 * 30;		// 半小时
	
	public final static long TWO_HOUR = 60 * 60 * 2 * 1000L;	//两小时
	
	public final static int ONE_DAY = 60 * 60 * 24;	//一天
	
	public final static int ONE_MONTH = 60 * 60 * 24 * 30;	//一月
	
	
	 //获取access_token的接口地址（GET） 限200（次/天）
	//public final static String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	 //公众号APPID
	//正式 wxd4be41f24fe20120
	public static String APPID="wxd4be41f24fe20120";
	
	 // 公众号APPSECRET
	//04abd4a5573974e5ec5a3288e3b6726e
	public static String APPSECRET="04abd4a5573974e5ec5a3288e3b6726e";
	
	//微信支付商户号 1501498901
	//商户平台登录账号 1501498901@1501498901
	//申请对应的小程序 一职很好
	//小程序appId     wxd4be41f24fe20120
	
	//微信支付商户号
	//线上 ： 1501498901
    public static final String MCH_ID = "1501498901";
    
    //微信支付API秘钥
    //线上  :  yizhihenhaowxzfk77025818d05fc144
    public static final String KEY = "yizhihenhaowxzfk77025818d05fc144";
    
    public static String GRANTTYPE="authorization_code";
    public static String REQUEST_TYPE="GET";
	public static String REQUEST_POST="POST";
	public static String OPENIDVALUE="openid";
	public static String TRADE_TYPE_JSAPI = "JSAPI";
	
	//微信获取openId接口
	public static final String WX_GET_OPENID = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={code}&grant_type=authorization_code";
	//微信小程序授权url
	public static final String WX_LOGIN = "https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html#wxloginobject";
	//微信获取个人信息
	public static final String WX_USER_INFO = "https://mp.weixin.qq.com/debug/wxadoc/dev/api/open.html#wxgetuserinfoobject";
	 //微信统一下单url
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //微信支付通知url
    public static final String NOTIFY_URL ="https://www.yizhihenhao.com/api/pay/success?str=";
    
    public static final String RETURN_SUCCESS = "SUCCESS";
    
    //投递简历
    public static final String delivery_resume_amout = "简历投递";
  //投递简历
    public static final String company_high_school_amout = "高中";
  //投递简历
    public static final String company_junior_college_amout = "大专";
  //投递简历
    public static final String company_undergraduate_amout = "本科";
  //投递简历
    public static final String company_master_amout = "硕士及以上";
	
    public static final String message_module = "验证码${code}，您正在进行身份验证，打死不要告诉别人哦！";
    
    public static final String accessKeyId = "LTAI8Sd9lsD0ZWL9";
    public static final String accessKeySecret = "lnnTwgvkGTlaHLzykhXJoi27zpIgSm";
    public static final String signName = "一职很好";
    public static final String identifyingTempleteCode = "SMS_139865027";
    public static final String registTempleteCode = "SMS_139865026";
    
}
