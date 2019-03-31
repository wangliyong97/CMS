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

    @Override
    public int updateByPrimaryKeySelective(Visit record) {
        return visitMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Long findVisitTimes(Visit visit) {
        return visitMapper.findVisitTimes(visit);
    }

    @Override
    public int insert(Visit record) {
        return visitMapper.insert(record);
    }

    @Override
    public int insertSelective(Visit record) {
        return visitMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return visitMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Visit selectByPrimaryKey(Integer id) {
        return visitMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Visit record) {
        return visitMapper.updateByPrimaryKey(record);
    }
}
