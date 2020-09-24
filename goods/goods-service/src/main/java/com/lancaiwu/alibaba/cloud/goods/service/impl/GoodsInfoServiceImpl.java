package com.lancaiwu.alibaba.cloud.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lancaiwu.alibaba.cloud.goods.pojo.entity.GoodsInfo;
import com.lancaiwu.alibaba.cloud.goods.resposity.mapper.GoodsInfoMapper;
import com.lancaiwu.alibaba.cloud.goods.service.GoodsInfoService;
import org.springframework.stereotype.Service;

@Service
public class GoodsInfoServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements GoodsInfoService {
    @Override
    public GoodsInfo getGoodsInfoById(Long id) {
        return baseMapper.selectById(id);
    }
}
