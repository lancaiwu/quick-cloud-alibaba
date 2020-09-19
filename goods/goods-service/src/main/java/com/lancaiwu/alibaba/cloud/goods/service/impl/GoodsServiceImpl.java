package com.lancaiwu.alibaba.cloud.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lancaiwu.alibaba.cloud.goods.pojo.entity.GoodsEntity;
import com.lancaiwu.alibaba.cloud.goods.resposity.mapper.GoodsMapper;
import com.lancaiwu.alibaba.cloud.goods.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements GoodsService {
    @Override
    public GoodsEntity getGoodsById(Long id) {
        return baseMapper.selectById(id);
    }
}
