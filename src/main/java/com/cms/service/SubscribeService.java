package com.cms.service;

import java.util.Date;

/**
 * Created by wangliyong on 2019/3/25.
 */
public interface SubscribeService {
    int saveSubscribeInformation(Integer userId, Integer activityId, Date reminder_time);

    boolean judgeIsSubscribe(Integer userId , Integer activityId);
}
