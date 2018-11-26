package com.hl.sellergoods.service;

import com.hl.pojo.TbTypeTemplate;
import com.hl.service.BaseService;
import com.hl.vo.PageResult;

public interface TypeTemplateService extends BaseService<TbTypeTemplate> {

    PageResult search(Integer page, Integer rows, TbTypeTemplate typeTemplate);

}