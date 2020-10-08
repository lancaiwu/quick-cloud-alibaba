package com.lancaiwu.alibaba.cloud.user.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author lancaiwu
 * @since 2020/9/25 10:20
 */
@Data
@TableName("t_user_info")
public class UserInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String mobile;
    private String nick;
    private String password;
    private Integer status;
    /**
     * 有效 1  无效 0
     */
    private Integer valid;
    private Date createTime;
    private Long createUser;
    private Date updateTime;
    private Long updateUser;
}
