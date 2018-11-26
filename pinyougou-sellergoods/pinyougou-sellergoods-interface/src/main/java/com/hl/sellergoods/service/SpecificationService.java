package com.hl.sellergoods.service;

import com.hl.pojo.TbSpecification;
import com.hl.service.BaseService;
import com.hl.vo.PageResult;
import com.hl.vo.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService extends BaseService<TbSpecification> {

    PageResult search(Integer page, Integer rows, TbSpecification specification);


    /**
     * 重写add方法
     * */
    void add(Specification specification);

    /**
     * 重写findOne方法
     * */
    Specification findOne(Long id);

    /**
     * 重写update方法
     * */
    void update(Specification specification);

    /**
     * 重写delete方法
     * */
    void deleteSpecificationByIds(Long[] ids);


    List<Map> selectOptionList();
}