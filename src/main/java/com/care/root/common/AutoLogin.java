package com.care.root.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

public class AutoLogin extends HandlerInterceptorAdapter{

	@Autowired MemberService ms;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie cookie = WebUtils.getCookie(request, "loginCookie");
		if( cookie != null) {
			MemberDTO dto = ms.getCookieUser(cookie.getValue());
			if(dto != null) {
				request.getSession().setAttribute("loginUser", dto.getId());
			}
		}
		return true;
	}
	
}
