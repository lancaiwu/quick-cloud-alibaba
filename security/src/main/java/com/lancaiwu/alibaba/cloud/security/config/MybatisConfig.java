package com.lancaiwu.alibaba.cloud.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @title：
 * @author: lancaiwu
 * @date: 2020年09月13日 12:53 下午
 * @description:
 */
@Configuration
@MapperScan("com.lancaiwu.alibaba.cloud.security.mapper")
public class MybatisConfig {
}
