package com.cms.service.impl;

import com.cms.dao.UserMapper;
import com.cms.pojo.User;
import com.cms.service.UserService;
import com.cms.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public int updateAccountPasswordById(Integer userId, String newPassword) {
        User user = new User();
        user.setId(userId);
        user.setPassword(newPassword);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateUserProfileById(Integer userId, String introduction, Date birthday, Integer gender) {
        User user = new User();
        user.setId(userId);
        user.setIntroduce(introduction);
        user.setBirthday(birthday);
        user.setGender(gender);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean sendEmailCode(String email, String code) {
        if(sendEmail(email,code)){
            return true;
        }
        return false;
    }

    @Override
    public boolean sendEmailHtmlContent(String email, String code) {
        if(sendEmailHtml(email,code)){
            return true;
        }
        return false;
    }

    public boolean sendEmail(String address, String code){
        boolean flag = true;
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.163.com");
            email.setCharset("UTF-8");
            email.addTo(address);
            email.setFrom("18260095973@163.com","Activity sharing");
            email.setAuthentication("18260095973@163.com","Gn270588");
            email.setSubject("Activity sharing 验证码");
            email.setMsg("尊敬的用户您好,您本次所需验证码是:" + code);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean sendEmailHtml(String address, String html){
        boolean flag = true;
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.163.com");
            email.setCharset("UTF-8");
            email.addTo(address);
            email.setFrom("18260095973@163.com","Activity sharing");
            email.setAuthentication("18260095973@163.com","Gn270588");
            email.setSubject("Activity sharing 预约提醒！");
            email.setHtmlMsg(html);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
