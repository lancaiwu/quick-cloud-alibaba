package com.lancaiwu.alibaba.cloud.goods.controller;

import com.lancaiwu.alibaba.cloud.goods.pojo.entity.GoodsEntity;
import com.lancaiwu.alibaba.cloud.goods.service.GoodsService;
import com.lancaiwu.alibaba.cloud.pojo.vo.APIResponse;
import com.lancaiwu.alibaba.cloud.goods.pojo.vo.GoodsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品
 */
@Api(tags = "商品")
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("根据商品id查询商品信息")
    @GetMapping("/getGoodsById")
    public APIResponse<GoodsVO> getGoodsById(@ApiParam("商品id") @RequestParam("id") Long id){
        GoodsEntity goodsEntity=goodsService.getGoodsById(id);
        GoodsVO goodsVO=new GoodsVO();
        if(goodsEntity!=null){
            BeanUtils.copyProperties(goodsEntity,goodsVO);
        }
        return APIResponse.data(goodsVO);
    }

}
