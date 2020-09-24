package com.lancaiwu.alibaba.cloud.goods.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lancaiwu
 * @since 2020/9/24 15:57
 */
@FeignClient("order-service")
public interface OrderFeign {
}
