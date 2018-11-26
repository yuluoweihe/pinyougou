package com.hl.mapper;

import com.hl.pojo.TbSpecification;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SpecificationMapper extends Mapper<TbSpecification> {

    List<Map> selectOptionList();
}
