package com.lancaiwu.alibaba.cloud.oauth2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author lancaiwu
 * @since 2020/10/10 14:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "authority")
public class SysAuthority implements GrantedAuthority {

    /**
     * 权限
     */
    private String authority;

    /**
     * 权限描述
     */
    private String desc;
}
