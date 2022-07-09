package com.perceptron.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.perceptron.service.TopMenuService;
import com.perceptron.vo.BoardInfoVO;


public class TopMenuInterceptor implements HandlerInterceptor{
	@Autowired
	@Lazy
	private TopMenuService topMenuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("[Interceptor] ===> TopMenuInterceptor.preHandle");
		
		List<BoardInfoVO> topMenuList=topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		return true;
	}
}
