package com.hl.sellergoods.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.mapper.BrandMapper;
import com.hl.pojo.TbBrand;
import com.hl.sellergoods.service.BrandService;
import com.hl.service.impl.BaseServiceImpl;
import com.hl.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 2018/11/22
 * 商品service实现类
 */
@Service(interfaceClass = BrandService.class)
public class BrandServiceImpl extends BaseServiceImpl<TbBrand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    /**
     * 查询商品列表信息
     */
    @Override
    public List<TbBrand> queryAll() {
        return brandMapper.queryAll();
    }

    /**
     * 测试通用分页插件
     * */
    @Override
    public List<TbBrand> testPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);

        return brandMapper.selectAll();
    }

    @Override
    public PageResult search(TbBrand brand, Integer page, Integer rows) {
        // 设置分页
        PageHelper.startPage(page,rows);

        // 模糊条件查询 select * from tb_brand where name like "%条件%" and first_char = "条件"
        Example example = new Example(TbBrand.class);

        // 创建查询条件对象；相当于where子句
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(brand.getFirstChar())){
            criteria.andEqualTo("firstChar",brand.getFirstChar());
        }
        if (!StringUtils.isEmpty(brand.getName())){
            criteria.andLike("name","%"+brand.getName()+"%");
        }

        // 如果有需要排序
        // example.orderBy("name");

        List<TbBrand> list = brandMapper.selectByExample(example);

        PageInfo<TbBrand> pageInfo = new PageInfo<>(list);


        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public List<Map<String,Object>> selectOptionList() {
        return brandMapper.selectOptionList();
    }
}
