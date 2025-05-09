package org.khuthon.agriserver.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Integer loginedId = Integer.parseInt(request.getSession().getAttribute("loginedId")==null?"0":request.getSession().getAttribute("loginedId").toString());
		System.out.println("loginedId : " + loginedId);
		request.setAttribute("loginedId", loginedId);
		request.setAttribute("isLogined", loginedId==0?false:true);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
