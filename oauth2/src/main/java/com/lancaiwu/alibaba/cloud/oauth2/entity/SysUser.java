package com.lancaiwu.alibaba.cloud.oauth2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这里可以看作数据库实体
 *
 * @author lancaiwu
 * @since 2020/10/10 14:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    private String id;

    private String username;

    private String password;

    private String test;
}
