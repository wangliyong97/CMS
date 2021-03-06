package com.cms.service;

import com.cms.pojo.ActivityType;

import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/1/20.
 */
public interface ActivityTypeService {

    List<ActivityType> selectActivityTypeListByPage(Map<String, Object> map);

    ActivityType selectActivityTypeById(Integer id);

    ActivityType selectActivityTypeByName(String typename);

    int updateActivityTypeSelective(ActivityType record);

    int deleteActivityTypeById(Integer id);

    int insertActivityType(ActivityType record);
}
