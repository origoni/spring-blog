package com.millky.blog.application.aop;

import org.springframework.core.MethodParameter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.millky.blog.domain.model.UserSession;

public class UserSessionArgumentResolver implements HandlerMethodArgumentResolver {

	private ConnectionRepository connectionRepository;

	public UserSessionArgumentResolver(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return UserSession.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		if (connection == null) {
			return null;
		}
		ConnectionData data = connection.createData();
		return new UserSession(data.getProviderUserId(), data.getImageUrl(), data.getDisplayName());
	}
}
