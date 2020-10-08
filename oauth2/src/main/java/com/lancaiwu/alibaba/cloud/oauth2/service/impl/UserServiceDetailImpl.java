package com.lancaiwu.alibaba.cloud.oauth2.service.impl;

import com.lancaiwu.alibaba.cloud.oauth2.pojo.model.UserDetailsModel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lancaiwu
 * @since 2020/10/6 10:19
 */
@Service
public class UserServiceDetailImpl implements UserDetailsService {
    /**
     * 参数s是前端输入的用户名
     * 通过该参数查找数据库，获取密码和角色权限，最后将这三个数据封装到UserDetails接口的实现类中返回
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE"));
        return new UserDetailsModel("user","user",authorities);
    }
}
