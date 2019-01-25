package com.cms.service;

import com.cms.pojo.BackendUser;

/**
 * Created by wangliyong on 2019/1/25.
 */
public interface BackendUserService {
    BackendUser findUserByLoginName(String name);
}
