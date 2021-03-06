package com.lancaiwu.alibaba.cloud.security.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:28
 * 自定义注销成功处理器：返回状态码200
 */
@Component
public class UrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        UrlResponse response = new UrlResponse();
        response.setSuccess(true);
        response.setCode("200");
        response.setMessage("Logout Success!!");
        response.setData(null);

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(response));
    }
}