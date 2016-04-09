package com.millky.blog.application.configuration;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.millky.blog.domain.service.factory.SearchServiceFactory;

@Configuration
public class BlogConfig {

	@Bean
	public ServiceLocatorFactoryBean searchFactoryServiceLocatorFactoryBean() {
		ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
		bean.setServiceLocatorInterface(SearchServiceFactory.class);
		return bean;
	}

	@Bean
	public SearchServiceFactory searchServiceFactory() {
		return (SearchServiceFactory) searchFactoryServiceLocatorFactoryBean().getObject();
	}
}
