package com.example.interceptors;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(LocalDateTime.now());
		request.setAttribute("message", "Inside interceptor");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println(LocalDateTime.now());
		System.out.println("postHandle");
		System.out.println("path: " + request.getRequestURI());
		System.out.println(request.getAttribute("newmessage"));

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		System.out.println(LocalDateTime.now());
		System.out.println("afterCompletion");
		System.out.println("path: " + request.getRequestURI());
		System.out.println(request.getAttribute("newmessage"));
		// request.setAttribute("message", "Inside interceptor");
	}

}
