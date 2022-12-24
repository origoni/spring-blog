package com.millky.blog.application.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version("v1.0.0")
                .title("SpringBlog from Millky")
                .description("<a href=\"/post/list\">Spring Boot base Open-source Blog</a> - APIs");
//				.contact("origoni@live.com");

        return new OpenAPI()
                .info(info);
    }
}