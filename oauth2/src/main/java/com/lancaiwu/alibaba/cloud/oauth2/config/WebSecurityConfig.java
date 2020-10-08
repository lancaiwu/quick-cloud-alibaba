package com.lancaiwu.alibaba.cloud.oauth2.config;

import com.lancaiwu.alibaba.cloud.oauth2.service.impl.UserServiceDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lancaiwu
 * @since 2020/10/6 09:42
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceDetailImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 这个方法是用来验证用户信息的。
     * 将前端输入的用户名和密码与数据库匹配，如果有这个用户才能认证成功。
     * 我们注入了一个UserServiceDetail，这个service的功能就是验证。
     * .passwordEncoder(passwordEncoder())是使用加盐解密。
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.  // 配置登陆页/login并允许访问
                formLogin().permitAll()
                // 登出页
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                // 其余所有请求全部需要鉴权认证
                .and().authorizeRequests().anyRequest().authenticated()
                // 由于使用的是JWT，我们这里不需要csrf
                .and().csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
