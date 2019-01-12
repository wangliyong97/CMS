package com.cms.service;

import java.util.List;
import java.util.Map;

import com.cms.pojo.Log;

/**
 * Created by wangliyong on 2019/1/12.
 */
public interface LogService {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectLogListByDate(Map<String, Object> map);

    List<?> selectUserLogByDate(Map<String, Object> map);
}
