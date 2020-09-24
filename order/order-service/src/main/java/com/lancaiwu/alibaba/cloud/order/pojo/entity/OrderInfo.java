package com.lancaiwu.alibaba.cloud.order.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lancaiwu
 * @since 2020/9/24 16:17
 */
@Data
@TableName("t_order_info")
public class OrderInfo implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String orderId;
    /**
     * 有效 1  无效 0
     */
    private Integer valid;
    private Date createTime;
    private Long createUser;
    private Date updateTime;
    private Long updateUser;
}
