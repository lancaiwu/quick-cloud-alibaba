package com.lancaiwu.alibaba.cloud.goods.bean.vo;

import com.lancaiwu.alibaba.cloud.goods.pojo.vo.GoodsInfoVO;
import com.lancaiwu.alibaba.cloud.user.bean.vo.UserInfoVO;

import java.util.Date;

/**
 * @author lancaiwu
 * @since 2020/9/25 10:39
 */
public class OrderInfoVO {
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
     * 商品信息
     */
    private GoodsInfoVO goodsInfo;
    /**
     * 商品件数
     */
    private Integer goodsNum;
    /**
     * 下单用户
     */
    private Long userId;
    /**
     * 下单用户信息
     */
    private UserInfoVO userInfoVO;
    /**
     * 有效 1  无效 0
     */
    private Integer valid;
    private Date createTime;
    private Long createUser;
    private Date updateTime;
    private Long updateUser;

    public Long getId() {
        return id;
    }

    public GoodsInfoVO getGoodsInfo() {
        return goodsInfo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setGoodsInfo(GoodsInfoVO goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public UserInfoVO getUserInfoVO() {
        return userInfoVO;
    }

    public void setUserInfoVO(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}
