package com.lancaiwu.alibaba.cloud.user.controller;

import com.lancaiwu.alibaba.cloud.pojo.vo.ApiResponse;
import com.lancaiwu.alibaba.cloud.user.bean.vo.UserInfoVO;
import com.lancaiwu.alibaba.cloud.user.pojo.entity.UserInfo;
import com.lancaiwu.alibaba.cloud.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lancaiwu
 * @since 2020/9/25 11:38
 */
@RestController
@RequestMapping("/userInfo")
@Api("用户")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    public ApiResponse<UserInfoVO> getUserById(@ApiParam("用户id") @RequestParam("id") Long id) {
        UserInfo userInfo = userInfoService.getById(id);
        UserInfoVO userInfoVO = new UserInfoVO();
        if (userInfo != null) {
            BeanUtils.copyProperties(userInfo, userInfoVO);
        }
        return ApiResponse.data(userInfoVO);
    }
}
