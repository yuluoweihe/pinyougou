package com.hl.sellergoods.service;

import com.hl.pojo.TbBrand;

import java.util.List;

/**
 * 2018/11/22
 * 商品service接口
 */
public interface BrandService {

    /**
     * 查询商品列表信息
     * */
    List<TbBrand> queryAll();
}
