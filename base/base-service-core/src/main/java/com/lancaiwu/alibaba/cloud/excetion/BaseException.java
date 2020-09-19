package com.lancaiwu.alibaba.cloud.excetion;

import com.lancaiwu.alibaba.cloud.enums.APIResponseEnums;

/**
 * Created with IntelliJ IDEA.
 * User: lancaiwu
 * Email: lancaiwu@gmail.com
 * Date: 2020-03-13
 * Time: 16:58
 * Description: 基础异常类
 */
public class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(Integer code) {
        super(APIResponseEnums.APIResponseEnum.fromValue(code));
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
