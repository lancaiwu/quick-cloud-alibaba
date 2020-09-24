package com.lancaiwu.alibaba.cloud.order.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lancaiwu.alibaba.cloud.order.pojo.entity.OrderInfo;
import com.lancaiwu.alibaba.cloud.order.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation("根据订单id查询订单信息")
    @GetMapping("getOrderById")
    public R<OrderInfo> getOrderById(Long id) {
        OrderInfo orderInfo = orderInfoService.getById(id);
        return R.ok(orderInfo);
    }

}
