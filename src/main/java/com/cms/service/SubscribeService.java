package com.cms.service;

import com.cms.pojo.Subscribe;

import java.util.Date;
import java.util.List;

/**
 * Created by wangliyong on 2019/3/25.
 */
public interface SubscribeService {
    int saveSubscribeInformation(Integer userId, Integer activityId, Date reminder_time);

    boolean judgeIsSubscribe(Integer userId , Integer activityId);

    boolean cancelSubscribe(Integer userId , Integer activityId);

    List<Subscribe> selectSubscribeActivityList(Integer userId);

    List<Subscribe> selectSubscribeFutureActivityList(Integer userId);
}
