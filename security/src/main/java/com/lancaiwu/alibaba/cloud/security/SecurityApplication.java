package com.lancaiwu.alibaba.cloud.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * SpringSecurity提供了多种登录认证的方式，由多种Filter过滤器来实现，比如：
 * <p>
 * BasicAuthenticationFilter 实现的是HttpBasic模式的登录认证
 * UsernamePasswordAuthenticationFilter 实现用户名密码的登录认证
 * RememberMeAuthenticationFilter 实现登录认证的“记住我”的功能
 * SmsCodeAuthenticationFilter 实现短信验证码登录认证
 * SocialAuthenticationFilter 实现社交媒体方式登录认证的处理
 * Oauth2AuthenticationProcessingFilter 和 Oauth2ClientAuthenticationProcessingFilter 实现Oauth2的鉴权方式
 *
 * @author lancaiwu
 */
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
