package com.yzhh.backstage.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @description:时间工具包
 * @projectName:staff-development
 * @className:DateUtils.java
 * @author:wentao
 * @createTime:2018年6月13日 上午9:58:07
 * @version 1.0.1
 */
public class DateUtils {

	// public final String format_1 = "";
	public static final String YYYYMMdd = "yyyy-MM-dd";
	public static final String yymmddhhmmss = "yyyy-MM-dd HH:mm:ss";
	
	public static final String no_yymmddhhmmss = "yyyyMMddHHmmss";
	
	public static final Long one_hour = 1000L * 60 * 60; 

	/**
	 * 
	 * @description:时间比较
	 * @return
	 * @author:王涛
	 * @createTime:2018年6月21日 下午5:01:28
	 */
	public static boolean isBeforeDate(Date startDate, Date endDate) {
		return startDate.before(endDate);
	}

	/**
	 * 
	 * @description:date类型转成String类型
	 * @param startDate
	 * @return
	 * @author:王涛
	 * @createTime:2018年6月21日 下午5:08:29
	 */
	public static String dateToString(Date startDate, String type) {
		if(startDate == null) {
			return null;
		}
		if (type == null) {
			type = YYYYMMdd;
		}
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(startDate);
	}

	public static String longToString(Long millis, String type) {
		if(millis == null) {
			return null;
		}
		if (type == null) {
			type = YYYYMMdd;
		}
		SimpleDateFormat df = new SimpleDateFormat(type);
		Date date = new Date();
		date.setTime(millis);
		return df.format(date);
	}


	/**
	 * 
	 * @description:String 转Date
	 * @param date
	 * @param type
	 * @return
	 * @author:王涛
	 * @throws ParseException
	 * @createTime:2018年6月21日 下午6:34:25
	 */
	public static Date stringToDate(String date, String type) throws ParseException {
		Date strtodate = null;

		if (type == null) {
			type = DateUtils.YYYYMMdd;
		}
		SimpleDateFormat df = new SimpleDateFormat(type);
		strtodate = df.parse(date);
		
		return strtodate;
	}
	
	public static Long stringToLong(String date, String type)  {
		Long strtodate = null;

		if (type == null) {
			type = DateUtils.YYYYMMdd;
		}
		SimpleDateFormat df = new SimpleDateFormat(type);
		try {
			strtodate = df.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return strtodate;
	}
	

	/**
	 * 
	 * @description:date转化成String
	 * @param date
	 * @return
	 * @author:王涛
	 * @createTime:2018年6月21日 下午3:33:39
	 */
	public static String dateToStringHms(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.YYYYMMdd);
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * @description:将时间转成国际时间格式
	 * @param date
	 * @return
	 * @author: 衷文涛
	 * @createTime:2018年6月13日 上午10:02:54
	 */
	public static String dateToUK(Date date) {
		String format = "d MMMMM yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
		return sdf.format(date);
	}

	/**
	 * @description: -- 日期扎转化mmddyyyy
	 * @author:谭农春
	 * @createTime: 2018/6/19 17:14
	 */

	public static String mmddyyy(Date date) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMdd, Locale.UK);
		return sdf.format(date);
	}

	/**
	 * @description:比对两个日期是否相同
	 * @param date1
	 * @param date2
	 * @return
	 * @author: 衷文涛
	 * @createTime:2018年6月23日 下午3:49:09
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}

	/**
	 * 
	 * @description:根据时间去判断是星期几
	 * @param date
	 *            时间的格式 dd/MM/yyyy
	 * @return
	 * @author:王涛
	 * @throws Exception
	 * @createTime:2018年6月27日 下午2:47:17
	 */
	public static Integer dateToWeek(String date) throws Exception {
		Integer repeat = -1;
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		// 获得一个日历
		Calendar cal = Calendar.getInstance();
		Date datet = null;
		try {
			datet = f.parse(date);
			cal.setTime(datet);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
		if (w < 0) {
			w = 0;
		}
		switch (weekDays[w]) {
		case "星期日":
			repeat = 6;
			break;
		case "星期一":
			repeat = 0;
			break;
		case "星期二":
			repeat = 1;
			break;
		case "星期三":
			repeat = 2;
			break;
		case "星期四":
			repeat = 3;
			break;
		case "星期五":
			repeat = 4;
			break;
		case "星期六":
			repeat = 5;
			break;
		default:
			throw new Exception("根据时间去判断是星期错误");
		}
		return repeat;
	}

	public static void main(String[] args) throws Exception {
		String d = "2018-7-20";
		System.out.println(DateUtils.stringToLong(d, YYYYMMdd));
	}
}
