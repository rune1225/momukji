package com.momukji.common.logincheck;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.momukji.mfront.domain.CustomerVO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession(false);
		
		if(session == null){
			response.sendRedirect(request.getContextPath()+"/customer/login");
		}
		
		CustomerVO cus = (CustomerVO)session.getAttribute("cus");
		
		if(cus == null){
			response.sendRedirect(request.getContextPath()+"/customer/login");
			return false;
		}
		
		return true;
	}

}