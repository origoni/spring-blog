package com.millky.blog.application.configuration;

import com.millky.blog.application.aop.CategoryInterceptor;
import com.millky.blog.application.aop.UserSessionArgumentResolver;
import com.millky.blog.application.aop.UserSessionInterceptor;
import com.millky.blog.infrastructure.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserSessionInterceptor());
        registry.addInterceptor(new CategoryInterceptor(categoryDao));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserSessionArgumentResolver());
    }
}
