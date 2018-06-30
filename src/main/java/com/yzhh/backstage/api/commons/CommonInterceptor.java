package com.yzhh.backstage.api.commons;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yzhh.backstage.api.constans.Constants;

/**
 * @description:通用拦截器
 * @projectName:staff-development
 * @className:CommonInterceptor.java
 * @author:wentao
 * @createTime:2018年6月8日 下午4:25:49
 * @version 1.0.1
 */
public class CommonInterceptor implements HandlerInterceptor {

	@SuppressWarnings("unused")
	private final String TOKEN = "token";

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
		
		//-------测试代码 start -----------------------//
		//LoginUser testUser = new LoginUser();
		
		String uri = request.getRequestURI();
        String ip = getIpAddr(request);
        
        request.getSession().setAttribute(Constants.IP, ip);
	
		 //如果是swagger的请求就直接通过
        if(uri.startsWith("/swagger-resources") || uri.startsWith("/v2") || uri.startsWith("/configuration")) {
        	return true;
        }
        
//        UserDTO user = (UserDTO)request.getSession().getAttribute(Constants.USER_LOGIN);
//        if(user != null && user.getRole().intValue() == RoleEnum.admin.getId()) {
//        	//checkRole()
//        }
       

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
		for (Cookie cookie : cookies) {
			if (str.equals(cookie.getName())) {
				return cookie.getValue();
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
	
//	//校验用户是否有该权限
//	private void checkAdminRole(String uri,String jurisdiction) {
//		String[] strs = jurisdiction.split(",");
//		
//		//
//		
//		if(true) {
//			//
//		}else if(true)
//	}
}
