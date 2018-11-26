package com.hl.mapper;

import com.hl.pojo.TbBrand;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 2018/11/22
 */
public interface BrandMapper extends Mapper<TbBrand> {
    /**
     * 查找商品列表
     * */
    List<TbBrand> queryAll();


    /**
     * 查询品牌列表，返回的数据格式符合select2格式
     * */
    List<Map<String,Object>> selectOptionList();

}
