package com.cms.service.impl;

import com.cms.dao.BackendUserMapper;
import com.cms.pojo.BackendUser;
import com.cms.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangliyong on 2019/1/25.
 */
@Service
public class BackendUserServiceImpl implements BackendUserService {
    @Autowired
    private BackendUserMapper backendUserMapper;

    @Override
    public BackendUser findUserByLoginName(String name) {
        return backendUserMapper.findUserByLoginName(name);
    }
}
