package com.lancaiwu.alibaba.cloud.pojo.vo;

import com.lancaiwu.alibaba.cloud.enums.APIResponseEnums;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口返回结果VO
 *
 * @author lancaiwu
 */
public class ApiResponse<T> implements Serializable {
    private int code;
    private String message;
    private boolean success;
    private Date timestamp;
    /**
     * 结果
     */
    private T data;

    public ApiResponse() {
    }

    private ApiResponse(T data) {
        this.data = data;
        this.code = APIResponseEnums.APIResponseEnum.SUCCESS.getCode();
        this.message = APIResponseEnums.APIResponseEnum.SUCCESS.getDesc();
        this.timestamp = new Date();
        this.success = true;
    }

    private ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = new Date();
        this.success = true;
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> data(T data) {
        return new ApiResponse<T>(data);
    }

    /**
     * 失败
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<T>(APIResponseEnums.APIResponseEnum.FAIL.getCode(), APIResponseEnums.APIResponseEnum.FAIL.getDesc());
    }

    /**
     * 异常
     *
     * @param apiResponseEnum
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> exception(APIResponseEnums.APIResponseEnum apiResponseEnum) {
        return new ApiResponse<T>(apiResponseEnum.getCode(), apiResponseEnum.getDesc());
    }

    public static <T> ApiResponse<T> exception(int code, String message) {
        return new ApiResponse<T>(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
