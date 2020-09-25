package com.lancaiwu.alibaba.cloud.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lancaiwu.alibaba.cloud.user.pojo.entity.UserInfo;
import com.lancaiwu.alibaba.cloud.user.resposity.mapper.UserInfoMapper;
import com.lancaiwu.alibaba.cloud.user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author lancaiwu
 * @since 2020/9/25 11:43
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
