package com.hl.sellergoods.service;

import com.hl.pojo.TbBrand;
import com.hl.service.BaseService;
import com.hl.vo.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 2018/11/22
 * 商品service接口
 */
public interface BrandService extends BaseService<TbBrand> {

    /**
     * 查询商品列表信息
     * */
    List<TbBrand> queryAll();

    /**
     * 测试通用分页插件
     * */
    List<TbBrand> testPage(Integer page, Integer size);

    PageResult search(TbBrand brand, Integer page, Integer rows);

    List<Map<String,Object>> selectOptionList();
}
