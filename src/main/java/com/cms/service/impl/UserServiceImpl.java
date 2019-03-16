package com.cms.service.impl;

import com.cms.dao.UserMapper;
import com.cms.pojo.User;
import com.cms.service.UserService;
import com.cms.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangliyong on 2019/2/20.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByLoginName(String name) {
        return userMapper.findUserByLoginName(name);
    }

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    /**
     * 注册时检查用户名合法性
     *
     * @param username 用户名
     * @return 合法返回true
     */
    public boolean checkUserName(String username) {
        if (StringUtil.isEmpty_(username)) return false;
        return true;
    }

    @Override
    public int updateUsernameById(Integer userId, String username) {
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateEmailById(Integer userId, String email) {
        User user = new User();
        user.setId(userId);
        user.setEmail(email);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateNicknameById(Integer userId, String nickname) {
        User user = new User();
        user.setId(userId);
        user.setNickname(nickname);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateAvatarById(Integer userId, String avatarPath) {
        User user = new User();
        user.setId(userId);
        user.setPicturePath(avatarPath);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
