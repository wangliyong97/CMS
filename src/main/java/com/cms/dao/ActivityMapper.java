package com.cms.dao;

import com.cms.pojo.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {

    List<Activity> selectGroupLikeActivityListByPage(Map<String, Object> map);

    List<Activity> selectLikeActivityListByPageWithBlobs(Map<String, Object> map);

    List<Activity> selectActivityByAllType();

    List<?>  selectActivityListByStatus();

    int refreshCache();

    Activity selectActivityById(Integer id);

    int updateActivitySelective(Activity record);

    int insertActivity(Activity record);
}