package com.lancaiwu.alibaba.cloud.order.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 *
 * @author lancaiwu
 * @since 2020/9/24 16:17
 */
@Data
@TableName("t_order_info")
public class OrderInfo implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品件数
     */
    private Integer goodsNum;
    /**
     * 下单用户
     */
    private Long userId;
    /**
     * 有效 1  无效 0
     */
    private Integer valid;
    private Date createTime;
    private Long createUser;
    private Date updateTime;
    private Long updateUser;
}
