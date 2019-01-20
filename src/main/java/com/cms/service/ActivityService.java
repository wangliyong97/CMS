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
}
