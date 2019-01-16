package com.cms.service.impl;

import com.cms.dao.ActivityMapper;
import com.cms.dao.ActivityTypeMapper;
import com.cms.pojo.Activity;
import com.cms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/1/15.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityTypeMapper activityTypeMapper;

    @Override
    public List<Activity> selectGroupLikeActivityListByPage(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return activityMapper.selectGroupLikeActivityListByPage(map);
    }
}
