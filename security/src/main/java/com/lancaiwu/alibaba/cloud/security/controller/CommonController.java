package com.lancaiwu.alibaba.cloud.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:35
 */
@RequestMapping(value = "/common")
@RestController
public class CommonController {

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/java")
    public String java() {
        return "java";
    }
}