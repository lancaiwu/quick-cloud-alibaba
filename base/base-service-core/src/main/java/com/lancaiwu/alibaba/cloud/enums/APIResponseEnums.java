package com.lancaiwu.alibaba.cloud.enums;

public interface APIResponseEnums {

    enum APIResponseEnum{
        SUCCESS(200,"成功"),
        FAIL(400,"失败"),
        PARAMS_ERROR(400, "参数错误"),
        NOT_FOUND(404, "请求路径不存在"),
        NOT_EXIST(405, "资源不存在"),
        SYSTEM_IS_BUSY(503, "系统繁忙,请稍后再试"),
        SYSTEM_NOT_EXIST(504, "远程服务访问失败"),

        /**
         * ================= 10000 - 19999 ========================
         * ================= 店铺中心 错 误============================
         */
        STORE_NOT_EXIST(10000, "店铺不存在"),

        /**
         * ================= 20000 - 29999 ========================
         * ================= 用户中心 错 误============================
         */
        USER_NOT_EXIST(20000, "用户不存在");

        APIResponseEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        private final int code;
        private final String desc;

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        /**
         * 根据code查询描述
         *
         * @param code
         * @return
         */
        public static String fromValue(Integer code) {
            for (APIResponseEnum e : values()) {
                if (e.code==code) {
                    return e.getDesc();
                }
            }
            return "未知返回码";
        }
    }
}
