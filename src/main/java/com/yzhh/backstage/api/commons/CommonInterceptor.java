package com.yzhh.backstage.api.commons;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.util.RedisUtil;

/**
 * @description:通用拦截器
 * @projectName:staff-development
 * @className:CommonInterceptor.java
 * @author:wentao
 * @createTime:2018年6月8日 下午4:25:49
 * @version 1.0.1
 */
public class CommonInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

	@Autowired
	private RedisUtil redisUtil;

	private Set<String> exceptionUrl = new HashSet<String>();

	{
		exceptionUrl.add("/api/login/verify/code");
		exceptionUrl.add("/api/mobile/verify/code");
		exceptionUrl.add("/ap/email/verify/code");
		exceptionUrl.add("/api/upload/file");
		
		exceptionUrl.add("/api/amount/gift");

		exceptionUrl.add("/api/admin/login");
		
		exceptionUrl.add("/api/wx/banner");
		

		exceptionUrl.add("/api/company/register");
		exceptionUrl.add("/api/company/forget/password");
		exceptionUrl.add("/api/company/login");
		exceptionUrl.add("/api/company/down/resume");
		exceptionUrl.add("/api/company/down/resume/list");
		
		exceptionUrl.add("/api/pay/success");
		exceptionUrl.add("/api/pay/fail");
		
		exceptionUrl.add("/api/wx/pay/success");

		exceptionUrl.add("/api/job/get/code");
		exceptionUrl.add("/api/job/get/code/token");
		exceptionUrl.add("/api/job/position/list");
		exceptionUrl.add("/api/job/position/info");
		exceptionUrl.add("/api/job/get/postion/camara");
		exceptionUrl.add("/api/job/company/info");
		exceptionUrl.add("/api/job/position/city");
	}

	/**
	 * @description:拦截请求
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @author: 衷文涛
	 * @createTime:2018年6月8日 下午4:26:13
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();
		String ip = getIpAddr(request);

		request.getSession().setAttribute(Constants.IP, ip);
		
		if(uri.equals("/api/wx/pay/success")) {
			logger.info("已收到微信返回url");
		}

		// 如果是swagger的请求就直接通过
		if (uri.startsWith("/swagger-resources") || uri.startsWith("/v2") || uri.startsWith("/configuration")) {
			return true;
		}
		
		if (exceptionUrl.contains(uri)) {
			return true;
		}

		UserDTO user = null;

		String token = request.getHeader(Constants.TOKEN);
		String identity = request.getHeader(Constants.IDENTITY);
		if (token != null) {
			switch (identity) {
			case "admin":
				user = (UserDTO) redisUtil.get(Constants.ADMIN_LOGIN + token);
				if (user == null) {
					throw new BizException(CommonError.USER_AUTH_ERROR);
				}
				// 刷新缓存
				redisUtil.set(Constants.ADMIN_LOGIN + user.getId(), user, Constants.TWO_HOUR);
				request.getSession().setAttribute(Constants.USER_LOGIN_SESSION, user);
				break;
			case "company":
				user = (UserDTO) redisUtil.get(Constants.COMPANY_LOGIN + token);
				if (user == null) {
					throw new BizException(CommonError.USER_AUTH_ERROR);
				}
				// 刷新缓存
				redisUtil.set(Constants.COMPANY_LOGIN + user.getId(), user, Constants.TWO_HOUR);
				request.getSession().setAttribute(Constants.USER_LOGIN_SESSION, user);
				break;
			case "jobSeeker":
				user = (UserDTO) redisUtil.get(Constants.JOB_SEEKER_LOGIN + token);
				if (user == null) {
					throw new BizException(CommonError.USER_AUTH_ERROR);
				}
				// 刷新缓存
				request.getSession().setAttribute(Constants.USER_LOGIN_SESSION, user);
				break;
			}
		}

		

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@SuppressWarnings("unused")
	private String getToken(HttpServletRequest request, String str) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (str.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * @description:获取用户ip
	 * @param request
	 * @return
	 * @author:衷文涛
	 * @createTime:2017年11月14日 下午1:38:04
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) {
			// "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * @description:直接写出response
	 * @param response
	 * @author:衷文涛
	 * @throws IOException
	 * @createTime:2018年3月7日 上午10:47:34
	 */
	@SuppressWarnings("unused")
	private void writeOutResponse(int status, ApiResponse apiResponse, HttpServletResponse response)
			throws IOException {
		response.setStatus(status);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String json = JSON.toJSONString(apiResponse);
		response.getWriter().write(json);
	}

}
