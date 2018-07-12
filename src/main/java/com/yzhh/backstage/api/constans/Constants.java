package com.yzhh.backstage.api.constans;

import java.util.HashMap;
import java.util.Map;

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
	public final static String USER_LOGIN = "user_login_";
	
	public final static String IP = "ip";
	
	public final static String phone_verification_code = "phone_verification_code_";
	
	public final static String email_verification_code = "email_verification_code_";
	
	public final static Long TEN_MINUTES = 60 * 10 * 1000L;		//10分钟
	
	public final static int HALF_HOUR = 60 * 30;		// 半小时
	
	public final static int TWO_HOUR = 60 * 60 * 2 * 1000;	//两小时
	
	public final static int ONE_DAY = 60 * 60 * 24;	//一天
	
	public final static int ONE_MONTH = 60 * 60 * 24 * 30;	//一月
	
	
	 //储存openid
	public static Map<String,String> OPENID =new HashMap<String,String>();
	
	 //获取access_token的接口地址（GET） 限200（次/天）
	public final static String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	 //公众号APPID
	//正式 wxd4be41f24fe20120
	public static String APPID="wxd4be41f24fe20120";
	
	 // 公众号APPSECRET
	//ed0e83c66c2f953d78c6d6a48ba8f9a7 
	public static String APPSECRET="ed0e83c66c2f953d78c6d6a48ba8f9a7";
	
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
	//微信ping 地址，来获取code
	public static String AUTHOR_MESSAGE_URL="http://fenxiao.ceshi.xingaokaowang.cn/message?";
	
	//微信授权页面，获取response_type=code的参数 
	//snsapi_userinfo(弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息，) 
	//snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid）
	public static String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx77025818d05fc144&redirect_uri=%&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	public static String CODE="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx77025818d05fc144&redirect_uri=http://fenxiao.ceshi.xingaokaowang.cn/&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	
	//获取openID 的拼接地址
	public static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token";
	//获取用户信息的授权地址
	public static String USER_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	 //微信统一下单url
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //微信支付通知url
    public static final String NOTIFY_URL = "http://fenxiao.ceshi.xingaokaowang.cn/user/noticSuccess?orderId=";
	
}
