package com.hl.sellergoods.service;

import com.hl.pojo.TbSpecificationOption;
import com.hl.service.BaseService;
import com.hl.vo.PageResult;

public interface SpecificationOptionService extends BaseService<TbSpecificationOption> {

    PageResult search(Integer page, Integer rows, TbSpecificationOption specificationOption);
}