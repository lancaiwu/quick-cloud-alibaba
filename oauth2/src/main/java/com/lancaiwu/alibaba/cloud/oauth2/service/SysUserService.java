package com.lancaiwu.alibaba.cloud.oauth2.service;

import com.lancaiwu.alibaba.cloud.oauth2.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * @author lancaiwu
 * @since 2020/10/10 14:55
 */
@Service
public class SysUserService {

    /**
     *  根据用户名查询用户
     * @param username
     * @return cn.poile.ucs.auth.entity.SysUser
     */
    public SysUser selectByUsername(String username) {
        return new SysUser("1","yaohw","$2a$10$CwIutywnbs9bifHaY3Ezu.gYkWi4Zano8gVPq08hXjal6.uj.Yzuy","测试字段，根据用户名查询");
    }

    /**
     * 根据手机号查询用户
     * @param mobile
     * @return cn.poile.ucs.auth.entity.SysUser
     */
    public SysUser selectByMobile(String mobile) {
        return new SysUser("2","yaohw2","$2a$10$CwIutywnbs9bifHaY3Ezu.gYkWi4Zano8gVPq08hXjal6.uj.Yzuy","测试字段，根据手机号查询");
    }
}
