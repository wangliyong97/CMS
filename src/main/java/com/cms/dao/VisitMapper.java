package com.cms.dao;

import com.cms.pojo.Visit;

import java.util.List;
import java.util.Map;

public interface VisitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Visit record);

    int insertSelective(Visit record);

    Long findVisitTimes(Visit visit);

    Visit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Visit record);

    int updateByPrimaryKey(Visit record);

    List<?> selectVisitListByDate(Map<String, Object> map);

    List<?>  selectVisitListByIp(Map<String, Object> map);

    List<Visit> selectLikeVisitListByPage(Map<String, Object> map);

    List<?> selectLikeVisitListGroupByIp(Map<String, Object> map);
}