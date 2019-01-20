package com.cms.dao;

import com.cms.pojo.ActivityType;

import java.util.List;
import java.util.Map;

public interface ActivityTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityType record);

    int insertSelective(ActivityType record);

    ActivityType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityType record);

    int updateByPrimaryKey(ActivityType record);

    ActivityType selectActivityTypeById(Integer id);

    List<ActivityType> selectActivityTypeListByPage(Map<String, Object> map);
}