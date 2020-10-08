package com.lancaiwu.alibaba.cloud.security.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:51
 * 用户角色资源管理
 */
@Data
@ToString
public class SecurityResponse implements Serializable {

    private boolean success;
    /**
     * 状态码：-1失败 1成功
     */
    private String code;
    /**
     * 信息说明
     */
    private String message;
    /**
     * 数据结果集
     */
    private Object data;
    /**
     * 总条数
     */
    private int total;

    public SecurityResponse() {
    }

    public SecurityResponse(boolean success, String code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public SecurityResponse(boolean success, String code, String message, Object data, int total) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }
}