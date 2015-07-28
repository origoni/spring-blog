package com.millky.blog.application.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.millky.blog.domain.model.UserSession;

public class UserSessionInterceptor extends HandlerInterceptorAdapter {

	private ConnectionRepository connectionRepository;

	public UserSessionInterceptor(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		Connection<Facebook> connection;

		try {
			connection = connectionRepository.findPrimaryConnection(Facebook.class);
		} catch (Exception e) {
			connection = null;
		}

		if (connection != null) {
			ConnectionData data = connection.createData();

			request.setAttribute("_USER", new UserSession(data.getProviderUserId(), data.getImageUrl(), data.getDisplayName()));
		}

		return true;
	}
}
