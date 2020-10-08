package com.lancaiwu.alibaba.cloud.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器
 * 资源服务器也就是服务程序，是需要访问的服务器
 *
 * @author lancaiwu
 * @since 2020/10/6 11:40
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // antMatcher 表示只能处理 /user 的请求
                .antMatcher("/user/**")
                .authorizeRequests()
                .antMatchers("/user/test1").permitAll()
                .antMatchers("/user/test2").authenticated()
        //.antMatchers("/user/test2").hasRole("USER")
        //.anyRequest().authenticated()
        ;
    }
}
