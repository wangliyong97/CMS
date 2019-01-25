package com.cms.service.impl;

import com.cms.dao.ActivityMapper;
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
    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public List<ActivityType> selectActivityTypeListByPage(Map<String, Object> map) {
        return activityTypeMapper.selectActivityTypeListByPage(map);
    }

    @Override
    public ActivityType selectActivityTypeById(Integer id) {
        return activityTypeMapper.selectActivityTypeById(id);
    }

    @Override
    public ActivityType selectActivityTypeByName(String typename) {
        return activityTypeMapper.selectActivityTypeByName(typename);
    }

    @Override
    public int updateActivityTypeSelective(ActivityType record) {
        if(activityTypeMapper.updateActivityTypeSelective(record)!=0){
            return activityMapper.refreshCache();
        }
        return 0;
    }

    @Override
    public int deleteActivityTypeById(Integer id) {
        if(activityTypeMapper.deleteByPrimaryKey(id)!=0){
            return activityMapper.refreshCache();
        }
        return 0;
    }

    @Override
    public int insertActivityType(ActivityType record) {
        return activityTypeMapper.insertSelective(record);
    }
}
