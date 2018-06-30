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
	public final static String USER_LOGIN = "user_login";
	
	public final static String IP = "ip";
	
	public final static int TEN_MINUTES = 60 * 10;		//10分钟
	
	public final static int HALF_HOUR = 60 * 30;		// 半小时
	
	public final static int TWO_HOUR = 60 * 60 * 2;	//两小时
	
	public final static int ONE_DAY = 60 * 60 * 24;	//一天
	
	public final static int ONE_MONTH = 60 * 60 * 24 * 30;	//一月
	/**
	 *  删除的pdf 临时存放地址
	 */
	public final static  String  UPLOAD_PDF="uploadfile/pdf";


	public final static  String PRE_TITLE =" Appraisal" ;
	public final static  String PRE_NAME="Name：";
	public  final static String  PRE_LEVEL ="Job Level: ";
	// 最后评估时间
	public final static  String  PRE_LASTAPPRAISAL ="Period of Appraial: ";
 // 升职
	public final  static String PROMOTION="promoted to %s on %s";
}
