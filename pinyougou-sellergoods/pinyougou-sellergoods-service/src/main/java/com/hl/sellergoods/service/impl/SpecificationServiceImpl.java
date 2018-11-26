package com.hl.sellergoods.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.mapper.SpecificationMapper;
import com.hl.mapper.SpecificationOptionMapper;
import com.hl.pojo.TbSpecification;
import com.hl.pojo.TbSpecificationOption;
import com.hl.sellergoods.service.SpecificationService;
import com.hl.service.impl.BaseServiceImpl;
import com.hl.vo.PageResult;
import com.hl.vo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SpecificationService.class)
public class SpecificationServiceImpl extends BaseServiceImpl<TbSpecification> implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public PageResult search(Integer page, Integer rows, TbSpecification specification) {
        PageHelper.startPage(page, rows);

        Example example = new Example(TbSpecification.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(specification.getSpecName())) {
            criteria.andLike("specName", "%" + specification.getSpecName() + "%");
        }

        List<TbSpecification> list = specificationMapper.selectByExample(example);
        PageInfo<TbSpecification> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加规格已经规格选项
     *
     * @param specification
     */
    @Override
    public void add(Specification specification) {
        // 添加规格
        specificationMapper.insertSelective(specification.getSpecification());

        // 添加规格选项
        if (specification.getSpecificationOptionList() != null && specification.getSpecificationOptionList().size() > 0) {

            for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
                // 设置规格id
                specificationOption.setSpecId(specification.getSpecification().getId());
                // 保存规格参数
                specificationOptionMapper.insertSelective(specificationOption);

            }
        }


    }


    /**
     * 回显修改规格和规格选项
     *
     * @param id
     * @return
     */
    @Override
    public Specification findOne(Long id) {

        Specification specification = new Specification();

        // 规格
        specification.setSpecification(specificationMapper.selectByPrimaryKey(id));

        //2、选项列表：根据规格id查询其所有的规格选项列表
        //select * from tb_specification_option where spec_id = ?
        TbSpecificationOption param = new TbSpecificationOption();
        param.setSpecId(id);
        List<TbSpecificationOption> specificationOptionList = specificationOptionMapper.select(param);

        specification.setSpecificationOptionList(specificationOptionList);
        return specification;
    }

    /**
     * 修改规格和规格选项
     * */
    @Override
    public void update(Specification specification) {
        // 更新规格
        specificationMapper.updateByPrimaryKeySelective(specification.getSpecification());

        // 删除原来的规格
        TbSpecificationOption param = new TbSpecificationOption();
        param.setSpecId(specification.getSpecification().getId());
        specificationOptionMapper.delete(param);

        // 新增规格选项
        if (specification.getSpecificationOptionList() != null && specification.getSpecificationOptionList().size() >= 1) {
            for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
                specificationOption.setSpecId(specification.getSpecification().getId());
                specificationOptionMapper.insertSelective(specificationOption);
            }
        }
    }

    /**
     * 删除规格和规格选项
     * */
    @Override
    public void deleteSpecificationByIds(Long[] ids) {
        //根据规格id集合删除规格及其每一个规格对应的所有的选项
        //1、根据规格id集合删除规格
        deleteByIds(ids);

        //2、删除每一个规格对应的所有的选项
        //sql：DELETE  FROM tb_specification_option WHERE spec_id  in (x, xx, xxx)
        Example example = new Example(TbSpecificationOption.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("specId", Arrays.asList(ids));

        specificationOptionMapper.deleteByExample(example);

    }

    @Override
    public List<Map> selectOptionList() {


        return specificationMapper.selectOptionList();
    }


}
