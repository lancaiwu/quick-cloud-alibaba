package com.lancaiwu.alibaba.cloud.security.controller;

import com.lancaiwu.alibaba.cloud.security.bean.AuthorityUser;
import com.lancaiwu.alibaba.cloud.security.bean.SecurityResponse;
import com.lancaiwu.alibaba.cloud.security.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:40
 * 用户管理
 */
@Slf4j
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping(value = "/security-manage")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增用户信息
     */
    @PostMapping(value = "/user-manage/add")
    public SecurityResponse add(@RequestBody  AuthorityUser authorityUser) {
        if (!ObjectUtils.isEmpty(authorityUser.getNickname()) && !ObjectUtils.isEmpty(authorityUser.getUsername()) && !ObjectUtils.isEmpty(authorityUser.getPassword())) {
            boolean existenceStatus = userService.usernameIsExistence(authorityUser.getUsername());
            if (!existenceStatus) {
                userService.addUserInfo(authorityUser);
                return new SecurityResponse(true, "1", "Add user success!!", "username: " + authorityUser.getUsername() + " add success!!");
            } else {
                return new SecurityResponse(false, "-1", "User already exists!!", "username: " + authorityUser.getUsername() + " already exists!!");
            }
        } else {
            return new SecurityResponse(false, "-1", "Incomplete information!!", "Incomplete information!!");
        }
    }

    /**
     * 为用户分配角色 (批量角色id:逗号分隔)
     */
    @PostMapping(value = "/user-manage/addRoles")
    public SecurityResponse addRolesForUser(String userId, String roleId) {
        try {
            if (!ObjectUtils.isEmpty(userId) && !ObjectUtils.isEmpty(roleId)) {
                userService.addRolesForUser(userId, roleId);
                return new SecurityResponse(true, "1", "User add role success!!", "userId: " + userId + ", roleId: " + roleId);
            } else {
                return new SecurityResponse(false, "-1", "User add role failure!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("addRolesForUser failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "User add role failure!!", "userId: " + userId + ", roleId: " + roleId);
        }
    }

    /**
     * 删除用户信息 (批量删除id 逗号分隔)
     */
    @PostMapping(value = "/user-manage/delete")
    public SecurityResponse delete(String id) {
        try {
            if (!ObjectUtils.isEmpty(id)) {
                userService.deleteUserInfo(id);
                return new SecurityResponse(true, "1", "Delete user success!!", "id: " + id);
            } else {
                return new SecurityResponse(false, "-1", "Delete user failure!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("deleteUserInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Delete user failure!!", "id: " + id);
        }
    }

    /**
     * 修改用户信息(管理员)
     */
    @PostMapping(value = "/user-manage/update")
    public SecurityResponse updateUserInfo(String nickname, String username, String email, String phone, String validTime, String remark) {
        return userService.updateUserInfo(nickname, username, email, phone, validTime, remark);
    }

    /**
     * 修改用户密码(管理员)
     */
    @PostMapping(value = "/user-manage/updatePassword")
    public SecurityResponse updateUserPassword(String userId, String username, String password) {
        return userService.updateUserPassword(userId, username, password);
    }

    /**
     * 获取所有用户信息 (分页加模糊查询)
     */
    @PostMapping(value = "/user-manage/findAll")
    public SecurityResponse findAll(String pageNum, String pageSize, String username, String nickname) {
        try {
            if (!ObjectUtils.isEmpty(pageNum) && !ObjectUtils.isEmpty(pageSize)) {
                SecurityResponse securityResponse = new SecurityResponse();
                userService.findAllUserInfo(pageNum, pageSize, username, nickname, securityResponse);
                return securityResponse;
            } else {
                return new SecurityResponse(false, "-1", "Incomplete information!!", "Incomplete information!!");
            }
        } catch (Exception e) {
            log.error("findAllUserInfo failure!! error={}", e.getMessage());
            return new SecurityResponse(false, "-1", "Find all user failure!!", e.getMessage());
        }
    }
}
