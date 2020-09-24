package com.lancaiwu.alibaba.cloud.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lancaiwu.alibaba.cloud.goods.pojo.entity.GoodsInfo;

public interface GoodsInfoService extends IService<GoodsInfo> {
    GoodsInfo getGoodsInfoById(Long id);
}
