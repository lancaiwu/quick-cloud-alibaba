package com.lancaiwu.alibaba.cloud.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lancaiwu.alibaba.cloud.order.pojo.entity.OrderInfo;
import com.lancaiwu.alibaba.cloud.order.resposity.mapper.OrderInfoMapper;
import com.lancaiwu.alibaba.cloud.order.service.OrderInfoService;
import org.springframework.stereotype.Service;

/**
 * @author lancaiwu
 * @since 2020/9/24 16:02
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
}
