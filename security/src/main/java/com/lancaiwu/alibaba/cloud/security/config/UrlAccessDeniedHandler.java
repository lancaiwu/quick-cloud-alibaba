package com.lancaiwu.alibaba.cloud.security.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:21
 * 自定义权限不足处理器：返回状态码403
 */
@Component
public class UrlAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        UrlResponse response = new UrlResponse();
        response.setSuccess(false);
        response.setCode("403");
        response.setMessage(e.getMessage());
        response.setData(null);

        httpServletResponse.setStatus(403);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(response));
    }
}
