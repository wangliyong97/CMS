package com.cms.service.impl;

import com.cms.dao.BackendUserMapper;
import com.cms.pojo.BackendUser;
import com.cms.service.IUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by wangliyong on 2019/1/9.
 */
@Service
public class UserManagerServiceImpl implements IUserManagerService{

    @Autowired
    private BackendUserMapper backendUserMapper;

    @Override
    public BackendUser findUserByLoginName(String name){
        return backendUserMapper.findUserByLoginName(name);
    }
}
