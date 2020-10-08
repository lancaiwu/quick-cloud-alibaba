package com.lancaiwu.alibaba.cloud.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author lancaiwu
 * @since 2020/10/6 11:44
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/test1")
    public String test() {
        return "test1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }
}
