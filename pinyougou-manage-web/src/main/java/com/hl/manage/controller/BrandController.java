package com.hl.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hl.pojo.TbBrand;
import com.hl.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2018/11/22
 *
 */
@RequestMapping("/brand")
@RestController
public class BrandController {

    @Reference
    private BrandService brandServiceImpl;
    /**
     * **需求**：在浏览器中访问
     * http://localhost:9100/brand/findAll.do
     * 输出所有数据库中对应的品牌列表json格式字符串
     * */
    @GetMapping("/findAll")
    public List<TbBrand> findAll(){

        return brandServiceImpl.queryAll();
    }
}
