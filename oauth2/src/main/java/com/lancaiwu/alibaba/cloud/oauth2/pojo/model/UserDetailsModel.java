package com.lancaiwu.alibaba.cloud.oauth2.pojo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author lancaiwu
 * @since 2020/10/6 10:22
 */
public class UserDetailsModel implements UserDetails {
    private String username;
    /**
     * 密码加盐处理
     */
    private String password;
    /**
     * 该用户的权限列表
     */
    private List<SimpleGrantedAuthority> authorityList;

    public UserDetailsModel(String username, String password, List<SimpleGrantedAuthority> authorityList) {
        this.username = username;
        this.password = password;
        this.authorityList = authorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
