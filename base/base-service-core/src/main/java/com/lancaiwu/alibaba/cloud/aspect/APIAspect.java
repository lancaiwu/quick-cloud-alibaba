//package com.lancaiwu.alibaba.cloud.aspect;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONException;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created with IntelliJ IDEA.
// * User: lancaiwu
// * Email: lancaiwu@gmail.com
// * Date: 2020-03-14
// * Time: 11:21
// * Description: 请求响应日志打印
// */
//@Aspect
//@Component
//@Order(1)
//@Slf4j
//public class APIAspect {
//
//    // 存放请求进来的时间
//    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();
//
//    @Pointcut("execution(public * com.lancaiwu.cloud.*.controller..*.*(..))")
//    public void webLog() {
//    }
//
//    @Before("webLog()")
//    public void apiBefore(JoinPoint joinPoint) {
//        // 接收到请求，记录请求内容
//        threadLocal.set(System.currentTimeMillis());
//
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        StringBuilder params = new StringBuilder();
//        Object[] args = joinPoint.getArgs();
//        if (args != null) {
//            for (Object obj : args) {
//                if (obj != null) {
//                    try {
//                        params.append(JSON.toJSONString(obj));
//                    } catch (JSONException e) {
//                        //  log.error("出现错误", e);
//                        params.append(obj.toString());
//                    }
//                    params.append("  ,  ");
//                }
//            }
//
//        }
//
//        // 记录下请求内容
//        log.info("-------------请求信息开始--------------");
//        log.info(String.format("发起请求的IP:%1$s,   请求的Url:%2$s,   请求方式:%3$s", request.getRemoteAddr(), request.getRequestURL().toString(), request.getMethod()));
//        log.info(String.format("请求参数:%1$s", params.toString()));
//        log.info("-------------请求信息结束--------------");
//    }
//
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void apiAfterReturning(Object ret) {
//        // 记录下响应内容
//        Long startTime = threadLocal.get();
//        log.info("-------------响应信息开始--------------");
//        log.info(String.format("response info: %1$s ", JSON.toJSONString(ret)));
//        log.info("-------------响应信息结束--------------");
//        log.info("-------------请求完成总耗时： " + (System.currentTimeMillis() - startTime) + "--------------");
//        log.info("===================================================");
//    }
//
//    /**
//     * @param joinPoint
//     * @return Object
//     * @throws Throwable
//     * @Description @Around 与   @AfterReturning 一起使用时，需要增加返回值，否则 @AfterReturning 入参为null
//     */
//    @Around("webLog()")
//    public Object apiAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("-------------Around 开始--------------");
//        Object retValue = joinPoint.proceed(); //执行完成目标方法
//        log.info("-------------Around 结束--------------");
//        return retValue;
//    }
//}
