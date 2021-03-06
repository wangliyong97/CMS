package com.cms.dao;

import com.cms.pojo.Subscribe;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SubscribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    Subscribe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);

    Subscribe judgeIsSubscribe(@Param("user_id") Integer user_id, @Param("activity_id") Integer activity_id);

    int cancelSubscribe(@Param("user_id") Integer user_id, @Param("activity_id") Integer activity_id);

    List<Subscribe> selectSubscribeActivityList(@Param("user_id") Integer user_id);

    List<Subscribe> selectSubscribeActivityListByDate();

    List<Subscribe> selectSubscribeFutureActivityList(@Param("user_id") Integer user_id);
}