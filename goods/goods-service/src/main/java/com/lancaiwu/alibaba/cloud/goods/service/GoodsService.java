package com.lancaiwu.alibaba.cloud.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lancaiwu.alibaba.cloud.goods.pojo.entity.GoodsEntity;

public interface GoodsService extends IService<GoodsEntity> {
    GoodsEntity getGoodsById(Long id);
}
