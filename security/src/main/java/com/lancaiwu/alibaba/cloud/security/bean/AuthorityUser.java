package com.lancaiwu.alibaba.cloud.security.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:42
 */
@Data
@TableName("t_user_info")
@ToString
public class AuthorityUser implements Serializable {

    @TableId
    private Long id;

    private String nickname; //昵称

    private String username; //用户名

    private String password; //密码

    private String email; //邮箱

    private String phone; //手机号

    private String validTime; //有效截止时间

    private String updateTime; //更新时间

    private String remark; //备注

}