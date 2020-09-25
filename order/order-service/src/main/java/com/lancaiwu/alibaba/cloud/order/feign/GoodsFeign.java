package com.lancaiwu.alibaba.cloud.order.feign;

import com.lancaiwu.alibaba.cloud.goods.pojo.vo.GoodsInfoVO;
import com.lancaiwu.alibaba.cloud.order.feign.fallback.GoodsFeignFallback;
import com.lancaiwu.alibaba.cloud.pojo.vo.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lancaiwu
 * @since 2020/9/24 16:26
 */
@FeignClient(value = "goods-service",fallback = GoodsFeignFallback.class)
public interface GoodsFeign {
    /**
     * 根据商品id查询商品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/goodsInfo/getGoodsById")
    ApiResponse<GoodsInfoVO> getGoodsById(@RequestParam("id") Long id);
}
