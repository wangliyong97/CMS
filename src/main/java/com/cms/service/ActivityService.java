package com.cms.service;

import com.cms.pojo.Activity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/1/15.
 */

public interface ActivityService {
    List<Activity> selectGroupLikeActivityListByPage(Map<String, Object> map);

    List<Activity> selectLikeActivityListByPageWithBlobs(Map<String, Object> map);

    Map<String,List<Activity>> selectActivityByAllType();

    List<?>  selectActivityListByStatus();

    Activity selectActivityUserById(Integer id);

    Activity selectActivityById(Integer id);

    Activity selectPrevActivity(Integer id);

    Activity selectNextActivity(Integer id);

    int updateActivitySelective(Activity activity);

    int insertActivity(Activity activity);

    List<?> selectActivityListByDate(Map<String, Object> map);

    List<?> selectActivityByClick();
}
