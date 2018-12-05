package com.hl.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.mapper.SpecificationOptionMapper;
import com.hl.mapper.TypeTemplateMapper;
import com.hl.pojo.TbSpecificationOption;
import com.hl.pojo.TbTypeTemplate;
import com.hl.sellergoods.service.TypeTemplateService;
import com.hl.service.impl.BaseServiceImpl;
import com.hl.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = TypeTemplateService.class)
public class TypeTemplateServiceImpl extends BaseServiceImpl<TbTypeTemplate> implements TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    @Autowired
    private SpecificationOptionMapper specificationOption;

    @Override
    public PageResult search(Integer page, Integer rows, TbTypeTemplate typeTemplate) {
        PageHelper.startPage(page, rows);

        Example example = new Example(TbTypeTemplate.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(typeTemplate.getName())){
            criteria.andLike("name", "%" + typeTemplate.getName() + "%");
        }

        List<TbTypeTemplate> list = typeTemplateMapper.selectByExample(example);
        PageInfo<TbTypeTemplate> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据分类模板id查询其对应的规格及其规格的选项
     * @param id 分类模板id
     * @return
     */
    @Override
    public List<Map> findSpecList(Long id) {
        // 查询规格选项
        TbTypeTemplate tbTypeTemplate = findOne(id);
        // 获取规格模板并转换为List
        List<Map> specList = JSONArray.parseArray(tbTypeTemplate.getSpecIds(),Map.class);
        for (Map map : specList) {
            // 查询规格对应的选项
            TbSpecificationOption param = new TbSpecificationOption();
            param.setSpecId(Long.parseLong(map.get("id").toString()));
            List<TbSpecificationOption> options = specificationOption.select(param);

            map.put("options",options);
        }
        return specList;

    }
}
