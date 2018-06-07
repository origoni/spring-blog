package com.millky.blog.application.aop;

import com.millky.blog.domain.model.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserSessionInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            String currentPrincipalName = authentication.getName();
            if (!currentPrincipalName.equals("anonymousUser")) {
                DefaultOAuth2User userDetails = (DefaultOAuth2User) authentication.getPrincipal();
                request.setAttribute("_USER", new UserSession(currentPrincipalName, "", (String) userDetails.getAttributes().get("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
