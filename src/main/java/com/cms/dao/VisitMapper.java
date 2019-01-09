package com.cms.dao;

import com.cms.pojo.Visit;

public interface VisitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Visit record);

    int insertSelective(Visit record);

    Visit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Visit record);

    int updateByPrimaryKey(Visit record);
}