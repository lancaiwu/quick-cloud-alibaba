package com.lancaiwu.alibaba.cloud.goods.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("t_goods_info")
@Data
public class GoodsInfo implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String skuCode;
    /**
     * 有效 1  无效 0
     */
    private Integer valid;
    private Date createTime;
    private Long createUser;
    private Date updateTime;
    private Long updateUser;

}
