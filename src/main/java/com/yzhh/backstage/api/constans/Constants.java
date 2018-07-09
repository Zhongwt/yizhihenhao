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
	public final static String USER_LOGIN = "user_login_";
	
	public final static String IP = "ip";
	
	public final static String phone_verification_code = "phone_verification_code_";
	
	public final static String email_verification_code = "email_verification_code_";
	
	public final static Long TEN_MINUTES = 60 * 10 * 1000L;		//10分钟
	
	public final static int HALF_HOUR = 60 * 30;		// 半小时
	
	public final static int TWO_HOUR = 60 * 60 * 2 * 1000;	//两小时
	
	public final static int ONE_DAY = 60 * 60 * 24;	//一天
	
	public final static int ONE_MONTH = 60 * 60 * 24 * 30;	//一月
	
	


}
