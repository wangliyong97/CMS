package com.cms.service.impl;

import com.cms.dao.VisitMapper;
import com.cms.pojo.Visit;
import com.cms.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/2/7.
 */
@Service
public class VisitServiceImpl implements VisitService{
    @Autowired
    private VisitMapper visitMapper;

    @Override
    public List<?> selectVisitListByDate(Map<String, Object> map) {
        return visitMapper.selectVisitListByDate(map);
    }

    @Override
    public List<?> selectVisitListByIp(Map<String, Object> map) {
        return visitMapper.selectVisitListByIp(map);
    }

    @Override
    public List<Visit> selectLikeVisitListByPage(Map<String, Object> map) {
        return visitMapper.selectLikeVisitListByPage(map);
    }

    @Override
    public List<?> selectLikeVisitListGroupByIp(Map<String, Object> map) {
        return visitMapper.selectLikeVisitListGroupByIp(map);
    }
}
