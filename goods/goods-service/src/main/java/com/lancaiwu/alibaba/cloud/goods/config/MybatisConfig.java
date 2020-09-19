package com.lancaiwu.alibaba.cloud.goods.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @title：
 * @author: lancaiwu
 * @date: 2020年09月13日 12:53 下午
 * @description:
 */
@Configuration
@MapperScan("com.lancaiwu.alibaba.cloud.goods.resposity.mapper")
public class MybatisConfig {
}
