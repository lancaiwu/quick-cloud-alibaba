package com.lancaiwu.alibaba.cloud.oauth2.service;

import com.lancaiwu.alibaba.cloud.oauth2.entity.SysAuthority;
import com.lancaiwu.alibaba.cloud.oauth2.entity.SysUser;
import com.lancaiwu.alibaba.cloud.oauth2.vo.UserDetailImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lancaiwu
 * @since 2020/10/10 14:56
 */

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("密码模式查询用户信息");
        SysUser sysUser = userService.selectByUsername(s);
        if (sysUser == null) {
            throw new UsernameNotFoundException("not found user:" + s);
        }
        UserDetailImpl userDetail = new UserDetailImpl();
        userDetail.setEnable(true);
        BeanUtils.copyProperties(sysUser,userDetail);
        //这里权限列表,这个为方便直接下（实际开发中查询用户时连表查询出权限）
        Set<SysAuthority> authoritySet = new HashSet<>();
        authoritySet.add(new SysAuthority("admin","管理员权限"));
        userDetail.setAuthorities(authoritySet);
        return userDetail;
    }

    /**
     * 这里模拟根据手机号查询用户
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        log.info("手机号模式查询用户信息");
        SysUser sysUser = userService.selectByMobile(mobile);
        if (sysUser == null) {
            throw new UsernameNotFoundException("not found mobile user:" + mobile);
        }
        UserDetailImpl userDetail = new UserDetailImpl();
        BeanUtils.copyProperties(sysUser,userDetail);
        userDetail.setAuthorities(new ArrayList<>());
        userDetail.setEnable(true);
        return userDetail;
    }
}
