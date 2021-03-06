package com.kh.spring.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.spring.member.model.vo.Member;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		if(loginMember==null) {
			request.setAttribute("msg",  "로그인 후 이용가능한 서비스입니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			.forward(request, response); 
			return false;
		}
		return super.preHandle(request, response, handler);
	}

}
