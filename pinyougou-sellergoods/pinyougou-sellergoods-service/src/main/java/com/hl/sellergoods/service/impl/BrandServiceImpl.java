package com.hl.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hl.mapper.BrandMapper;
import com.hl.pojo.TbBrand;
import com.hl.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 2018/11/22
 * 商品service实现类
 */
@Service(interfaceClass = BrandService.class)
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    /**
     * 查询商品列表信息
     */
    @Override
    public List<TbBrand> queryAll() {
        return brandMapper.queryAll();
    }
}
