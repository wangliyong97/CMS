package com.cms.service.impl;

import com.cms.dao.ActivityMapper;
import com.cms.dao.ActivityTypeMapper;
import com.cms.pojo.Activity;
import com.cms.pojo.ActivityType;
import com.cms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangliyong on 2019/1/15.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityTypeMapper activityTypeMapper;

    public static final AtomicInteger count = new AtomicInteger(0);

    @Override
    public List<Activity> selectGroupLikeActivityListByPage(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return activityMapper.selectGroupLikeActivityListByPage(map);
    }

    @Override
    public List<Activity> selectLikeActivityListByPageWithBlobs(Map<String, Object> map){
        return activityMapper.selectLikeActivityListByPageWithBlobs(map);
    }

    @Override
    public Map<String, List<Activity>> selectActivityByAllType() {
        // TODO Auto-generated method stub
        Map<String, List<Activity>> map = new HashMap<>();
        List<Activity> list = activityMapper.selectActivityByAllType();
        for (Activity activity : list) {
            if (map.containsKey(activity.getType().getTypename())) {
                map.get(activity.getType().getTypename()).add(activity);
            } else {
                List<Activity> aList = new ArrayList<>();
                aList.add(activity);
                map.put(activity.getType().getTypename(), aList);
            }
        }
        return map;
    }

    @Override
    public List<?> selectActivityListByStatus() {
        return activityMapper.selectActivityListByStatus();
    }

    @Override
    public Activity selectActivityById(Integer id) {
        Activity activity = activityMapper.selectActivityById(id);
        if (activity != null) {
            synchronized (activity) {
                count.getAndIncrement();
                activity.setClicknum(activity.getClicknum() + count.get());
                // 十条记录 往数据库写入 一次
                if (count.get() >= 5) {
                    activityMapper.updateActivitySelective(activity);
                    count.set(0);
                }
            }
        }
        return activity;
    }

    @Override
    public int updateActivitySelective(Activity activity) {
        Activity oldActivity = activityMapper.selectActivityById(activity.getId());
        if (activity.getType() == null && activity.getStatus() == null) {
            return activityMapper.updateActivitySelective(activity);
        }
        if (activityMapper.updateActivitySelective(activity) != 0) {
            if (activity.getStatus() == 1) {
                if (oldActivity.getStatus() == 1) {
                    ActivityType oldActivityType = activityTypeMapper.selectActivityTypeById(oldActivity.getType().getId());
                    oldActivityType.setNum(oldActivityType.getNum() - 1);
                    activityTypeMapper.updateActivityTypeSelective(oldActivityType);
                }
                ActivityType activityType = null;
                if (activity.getType() == null) {
                    activityType = activityTypeMapper.selectActivityTypeById(oldActivity.getType().getId());
                } else {
                    activityType = activityTypeMapper.selectActivityTypeById(activity.getType().getId());
                }
                activityType.setNum(activityType.getNum() + 1);
                return activityTypeMapper.updateActivityTypeSelective(activityType);
            } else {
                if (oldActivity.getStatus() == 1) {
                    ActivityType oldActivityType = activityTypeMapper.selectActivityTypeById(oldActivity.getType().getId());
                    oldActivityType.setNum(oldActivityType.getNum() - 1);
                    return activityTypeMapper.updateActivityTypeSelective(oldActivityType);
                }
                return 1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public int insertActivity(Activity activity) {
        if (activityMapper.insertActivity(activity) != 0) {
            if (activity.getStatus() == 1) {
                ActivityType activityType = activityTypeMapper.selectActivityTypeById(activity.getType().getId());
                activityType.setNum(activityType.getNum() + 1);
                return activityTypeMapper.updateActivityTypeSelective(activityType);
            } else {
                return 1;
            }
        }
        return 0;
    }

}
