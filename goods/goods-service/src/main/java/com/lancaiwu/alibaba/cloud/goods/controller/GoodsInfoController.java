package com.lancaiwu.alibaba.cloud.goods.controller;

import com.lancaiwu.alibaba.cloud.goods.pojo.entity.GoodsInfo;
import com.lancaiwu.alibaba.cloud.goods.service.GoodsInfoService;
import com.lancaiwu.alibaba.cloud.pojo.vo.ApiResponse;
import com.lancaiwu.alibaba.cloud.goods.pojo.vo.GoodsInfoVO;
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
 * @author lancaiwu
 */
@Api(tags = "商品")
@RestController
@RequestMapping("goodsInfo")
public class GoodsInfoController {

    @Autowired
    private GoodsInfoService goodsInfoService;

    @ApiOperation("根据商品id查询商品信息")
    @GetMapping("/getGoodsById")
    public ApiResponse<GoodsInfoVO> getGoodsById(@ApiParam("商品id") @RequestParam("id") Long id){
        GoodsInfo goodsInfo = goodsInfoService.getGoodsInfoById(id);
        GoodsInfoVO goodsInfoVO =new GoodsInfoVO();
        if(goodsInfo !=null){
            BeanUtils.copyProperties(goodsInfo, goodsInfoVO);
        }
        return ApiResponse.data(goodsInfoVO);
    }

}
