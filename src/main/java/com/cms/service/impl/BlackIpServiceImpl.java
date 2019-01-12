package com.cms.service.impl;

import com.cms.dao.BlackIpMapper;
import com.cms.pojo.BlackIp;
import com.cms.service.BlackIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/1/12.
 */
@Service
public class BlackIpServiceImpl implements BlackIpService{
    @Autowired
    private BlackIpMapper blackIpMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return blackIpMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlackIp record) {
        // TODO Auto-generated method stub
        return blackIpMapper.insert(record);
    }

    @Override
    public int insertSelective(BlackIp record) {
        // TODO Auto-generated method stub
        return blackIpMapper.insertSelective(record);
    }

    @Override
    public BlackIp selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return blackIpMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlackIp record) {
        // TODO Auto-generated method stub
        return blackIpMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlackIp record) {
        // TODO Auto-generated method stub
        return blackIpMapper.updateByPrimaryKey(record);
    }

    @Override
    public BlackIp selectBlackIpByIp(String ip) {
        // TODO Auto-generated method stub
        return blackIpMapper.selectBlackIpByIp(ip);
    }

    @Override
    public Long selectAllBlackIpCount() {
        // TODO Auto-generated method stub
        return blackIpMapper.selectAllBlackIpCount();
    }

    @Override
    public List<BlackIp> selectLikeBlackIpListByPage(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return blackIpMapper.selectLikeBlackIpListByPage(map);
    }
}
