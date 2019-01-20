package com.cms.service.impl;

import com.cms.dao.ActivityTypeMapper;
import com.cms.pojo.ActivityType;
import com.cms.service.ActivityService;
import com.cms.service.ActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/1/20.
 */
@Service
public class ActivityTypeServiceImpl implements ActivityTypeService{
    @Autowired
    private ActivityTypeMapper activityTypeMapper;
    @Override
    public List<ActivityType> selectActivityTypeListByPage(Map<String, Object> map) {
        return activityTypeMapper.selectActivityTypeListByPage(map);
    }
}
