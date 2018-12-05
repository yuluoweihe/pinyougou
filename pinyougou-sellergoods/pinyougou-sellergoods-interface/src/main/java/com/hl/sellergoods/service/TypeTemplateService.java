package com.hl.sellergoods.service;

import com.hl.pojo.TbTypeTemplate;
import com.hl.service.BaseService;
import com.hl.vo.PageResult;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService extends BaseService<TbTypeTemplate> {

    PageResult search(Integer page, Integer rows, TbTypeTemplate typeTemplate);

    /**
     * 根据分类模板id查询其对应的规格及其规格的选项
     * @param id 分类模板id
     * @return
     */
    List<Map> findSpecList(Long id);
}