package com.lancaiwu.alibaba.cloud.gateway.controller;

import com.lancaiwu.alibaba.cloud.gateway.vo.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lancaiwu
 * @since 2020/9/25 16:50
 */
@RestController
public class FallbackController {
    /**
     * 服务不可用的转发响应
     *
     * @return
     */
    @GetMapping("fallback")
    public ApiResponse<Void> fallback() {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(503);
        response.setSuccess(false);
        response.setTimestamp(new Date());
        response.setMessage("服务暂时不可用");
        return response;
    }
}
