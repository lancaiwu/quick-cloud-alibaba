package com.lancaiwu.alibaba.cloud.order.feign.fallback;

import com.lancaiwu.alibaba.cloud.enums.APIResponseEnums;
import com.lancaiwu.alibaba.cloud.order.feign.UserFeign;
import com.lancaiwu.alibaba.cloud.pojo.vo.ApiResponse;
import com.lancaiwu.alibaba.cloud.user.bean.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lancaiwu
 * @since 2020/9/25 10:47
 */
@Component
@Slf4j
public class UserFeignFallback implements UserFeign {

    @Override
    public ApiResponse<UserInfoVO> getUserById(Long id) {
        log.error(" getUserById  fallback  ");
        return ApiResponse.exception(APIResponseEnums.APIResponseEnum.SYSTEM_NOT_EXIST);
    }
}
