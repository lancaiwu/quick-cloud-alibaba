package com.lancaiwu.alibaba.cloud.security.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:24
 * 自定义登录失败处理器：返回状态码402
 */
@SuppressWarnings("Duplicates")
@Component
public class UrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        UrlResponse response = new UrlResponse();
        response.setSuccess(false);
        response.setCode("402");
        response.setMessage(e.getMessage());
        response.setData(null);

        httpServletResponse.setStatus(402);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(response));
    }
}