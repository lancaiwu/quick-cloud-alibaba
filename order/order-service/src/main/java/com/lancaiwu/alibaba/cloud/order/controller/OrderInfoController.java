package com.lancaiwu.alibaba.cloud.order.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lancaiwu.alibaba.cloud.goods.bean.vo.OrderInfoVO;
import com.lancaiwu.alibaba.cloud.goods.pojo.vo.GoodsInfoVO;
import com.lancaiwu.alibaba.cloud.order.feign.GoodsFeign;
import com.lancaiwu.alibaba.cloud.order.feign.UserFeign;
import com.lancaiwu.alibaba.cloud.order.pojo.entity.OrderInfo;
import com.lancaiwu.alibaba.cloud.order.service.OrderInfoService;
import com.lancaiwu.alibaba.cloud.pojo.vo.ApiResponse;
import com.lancaiwu.alibaba.cloud.user.bean.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单
 *
 * @author lancaiwu
 * @since 2020/9/24 16:01
 */
@RestController
@RequestMapping("orderInfo")
@Api("订单")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private GoodsFeign goodsFeign;

    @Autowired
    private UserFeign userFeign;

    @ApiOperation("根据订单id查询订单信息")
    @GetMapping("getOrderById")
    public R<OrderInfoVO> getOrderById(Long id) {
        OrderInfo orderInfo = orderInfoService.getById(id);
        if (orderInfo == null) {
            return R.failed("不存在该订单");
        }

        OrderInfoVO orderInfoVO = new OrderInfoVO();
        BeanUtils.copyProperties(orderInfo, orderInfoVO);
        // 查询商品信息
        ApiResponse<GoodsInfoVO> goodsInfoApiResponse = goodsFeign.getGoodsById(orderInfo.getGoodsId());
        if (goodsInfoApiResponse.isSuccess()) {
            orderInfoVO.setGoodsInfo(goodsInfoApiResponse.getData());
        }
        // 查询下单人信息
        ApiResponse<UserInfoVO> userInfoApiResponse = userFeign.getUserById(orderInfo.getUserId());
        if (userInfoApiResponse.isSuccess()) {
            orderInfoVO.setUserInfoVO(userInfoApiResponse.getData());
        }

        return R.ok(orderInfoVO);
    }

}
