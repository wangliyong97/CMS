package com.cms.service;

import com.cms.pojo.Visit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/2/7.
 */
public interface VisitService {
    List<?> selectVisitListByDate(Map<String, Object> map);

    List<?>  selectVisitListByIp(Map<String, Object> map);

    List<Visit> selectLikeVisitListByPage(Map<String, Object> map);

    List<?> selectLikeVisitListGroupByIp(Map<String, Object> map);

    Long findVisitTimes(Visit visit);

    int insert(Visit record);
}
