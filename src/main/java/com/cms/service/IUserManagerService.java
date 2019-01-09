package com.cms.service;

import com.cms.pojo.BackendUser;

/**
 * Created by wangliyong on 2019/1/9.
 */
public interface IUserManagerService {

    BackendUser findUserByLoginName(String name);
}
