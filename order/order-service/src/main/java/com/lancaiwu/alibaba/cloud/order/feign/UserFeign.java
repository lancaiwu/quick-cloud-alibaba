package com.lancaiwu.alibaba.cloud.order.feign;

import com.lancaiwu.alibaba.cloud.order.feign.fallback.UserFeignFallback;
import com.lancaiwu.alibaba.cloud.pojo.vo.ApiResponse;
import com.lancaiwu.alibaba.cloud.user.bean.vo.UserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户
 *
 * @author lancaiwu
 * @since 2020/9/25 11:47
 */
@FeignClient(value = "user-service",fallback = UserFeignFallback.class)
public interface UserFeign {
    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/userInfo/getUserById")
    ApiResponse<UserInfoVO> getUserById(@RequestParam("id") Long id);

}
