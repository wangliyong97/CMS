package com.cms.dao;

import com.cms.pojo.Subscribe;
import org.apache.ibatis.annotations.Param;

public interface SubscribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    Subscribe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);

    Subscribe judgeIsSubscribe(@Param("user_id") Integer user_id, @Param("activity_id") Integer activity_id);
}