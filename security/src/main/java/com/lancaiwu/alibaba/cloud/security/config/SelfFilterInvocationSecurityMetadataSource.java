package com.lancaiwu.alibaba.cloud.security.config;

import com.lancaiwu.alibaba.cloud.security.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:18
 * * 动态获取url权限配置
 */
@Slf4j
@Component
public class SelfFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private UserService userService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        Set<ConfigAttribute> set = new HashSet<>();
        // 获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        log.info("requestUrl >> {}", requestUrl);
        List<String> menuUrl = userService.findAllMenuUrl();
        for (String url : menuUrl) {
            if (antPathMatcher.match(url, requestUrl)) {
                List<String> roleNames = userService.findRoleNameByMenuUrl(url); //当前请求需要的权限
                roleNames.forEach(roleName -> {
                    SecurityConfig securityConfig = new SecurityConfig(roleName);
                    set.add(securityConfig);
                });
            }
        }
        if (ObjectUtils.isEmpty(set)) {
            return SecurityConfig.createList("ROLE_WRONG");
        }
        return set;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}