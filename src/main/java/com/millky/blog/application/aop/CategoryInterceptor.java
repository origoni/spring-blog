package com.millky.blog.application.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.millky.blog.infrastructure.dao.CategoryDao;

public class CategoryInterceptor implements HandlerInterceptor {//extends HandlerInterceptorAdapter {

	private CategoryDao categoryDao;

	public CategoryInterceptor(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		request.setAttribute("_CATEGORY_LIST", categoryDao.findAll());

		return true;
	}
}
