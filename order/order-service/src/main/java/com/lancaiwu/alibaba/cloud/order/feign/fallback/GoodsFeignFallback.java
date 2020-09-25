package com.lancaiwu.alibaba.cloud.order.feign.fallback;

import com.lancaiwu.alibaba.cloud.enums.APIResponseEnums;
import com.lancaiwu.alibaba.cloud.goods.pojo.vo.GoodsInfoVO;
import com.lancaiwu.alibaba.cloud.order.feign.GoodsFeign;
import com.lancaiwu.alibaba.cloud.pojo.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lancaiwu
 * @since 2020/9/25 10:47
 */
@Component
@Slf4j
public class GoodsFeignFallback implements GoodsFeign {
    @Override
    public ApiResponse<GoodsInfoVO> getGoodsById(Long id) {
        log.error(" getGoodsById  fallback  ");
        return ApiResponse.exception(APIResponseEnums.APIResponseEnum.SYSTEM_NOT_EXIST);
    }
}
