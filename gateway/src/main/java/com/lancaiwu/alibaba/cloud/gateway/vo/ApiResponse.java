package com.lancaiwu.alibaba.cloud.gateway.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口返回结果VO
 *
 * @author lancaiwu
 */
@Data
public class ApiResponse<T> implements Serializable {
    private int code;
    private String message;
    private boolean success;
    private Date timestamp;
    /**
     * 结果
     */
    private T data;
}
