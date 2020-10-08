package com.lancaiwu.alibaba.cloud.security.config;

import com.alibaba.fastjson.JSON;
import com.lancaiwu.alibaba.cloud.security.service.impl.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:27
 * 自定义登录成功处理器并生成token：响应状态码200及token
 */
@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * token响应头
     */
    @Value("${jwt.token-header}")
    private String tokenHeader;
    /**
     * token前缀
     */
    @Value("${jwt.token-prefix}")
    private String tokenPrefix;
    /**
     * token秘钥
     */
    @Value("${jwt.token-secret}")
    private String tokenSecret;
    /**
     * token过期时间
     */
    @Value("${jwt.token-expiration}")
    private Long tokenExpiration;

    @Resource
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setCharacterEncoding("UTF-8");
        UrlResponse response = new UrlResponse();
        response.setSuccess(true);
        response.setCode("200");
        response.setMessage("Login Success!");

        String username = (String) authentication.getPrincipal(); //表单输入的用户名
        Map<String, Object> userInfo = userService.findMenuInfoByUsername(username, response); //用户可访问的菜单信息
        response.setData(userInfo);

        // 生成token并设置响应头
        Claims claims = Jwts.claims();
        claims.put("role", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username) //设置用户名
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) //设置token过期时间
                .signWith(SignatureAlgorithm.HS512, tokenSecret).compact(); //设置token签名算法及秘钥
        httpServletResponse.addHeader(tokenHeader, tokenPrefix + " " + token); //设置token响应头

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(response));
    }
}