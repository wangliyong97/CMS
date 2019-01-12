package com.cms.dao;

import com.cms.pojo.BlackIp;

import java.util.List;
import java.util.Map;

public interface BlackIpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlackIp record);

    int insertSelective(BlackIp record);

    BlackIp selectByPrimaryKey(Integer id);

    BlackIp selectBlackIpByIp(String ip);

    int updateByPrimaryKeySelective(BlackIp record);

    int updateByPrimaryKey(BlackIp record);

    List<BlackIp> selectLikeBlackIpListByPage(Map<String, Object> map);

    Long selectAllBlackIpCount();
}