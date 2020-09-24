package com.lancaiwu.alibaba.cloud.order.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lancaiwu
 * @since 2020/9/24 16:26
 */
@FeignClient("goods-service")
public interface GoodsFeign {
}
