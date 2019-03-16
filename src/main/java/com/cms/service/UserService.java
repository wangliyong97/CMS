package com.cms.service;

import com.cms.pojo.User;

/**
 * Created by wangliyong on 2019/2/20.
 */
public interface UserService {
    User findUserByLoginName(String name);

    User findUserByEmail(String email);

    int insert(User user);

    boolean checkUserName(String username);

    int updateUsernameById(Integer userId, String username);

    int updateEmailById(Integer userId, String email);

    int updateNicknameById(Integer userId, String nickname);

    int updateAvatarById(Integer userId, String avatarPath);

    User selectUserById(Integer id);

}
