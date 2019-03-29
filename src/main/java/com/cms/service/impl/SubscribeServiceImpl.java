package com.cms.service.impl;

import com.cms.dao.SubscribeMapper;
import com.cms.pojo.Subscribe;
import com.cms.service.SubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangliyong on 2019/3/25.
 */
@Slf4j
@Service
public class SubscribeServiceImpl implements SubscribeService{
    @Autowired
    private SubscribeMapper subscribeMapper;

    @Override
    public int saveSubscribeInformation(Integer userId, Integer activityId, Date reminder_time) {
        Subscribe subscribe = new Subscribe(userId,activityId,1,new Date(),reminder_time);
        return subscribeMapper.insert(subscribe);
    }

    @Override
    public boolean judgeIsSubscribe(Integer userId, Integer activityId) {
        Subscribe result = subscribeMapper.judgeIsSubscribe(userId,activityId);
        if(result != null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<Subscribe> selectSubscribeActivityList(Integer userId) {
        return subscribeMapper.selectSubscribeActivityList(userId);
    }

    @Override
    public List<Subscribe> selectSubscribeFutureActivityList(Integer userId) {
        return subscribeMapper.selectSubscribeFutureActivityList(userId);
    }
}
