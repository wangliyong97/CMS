package com.cms.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String question;

    private String answer;

    private Integer role;

    private String nickname;

    private Integer gender;

    private Date birthday;

    private String picturePath;

    private String introduce;

    private Date createTime;

    private Date updateTime;

    private Integer haspermission;

    public User(Integer id, String username, String password, String email, String question, String answer, Integer role, String nickname, Integer gender, Date birthday, String picturePath, String introduce, Date createTime, Date updateTime, Integer haspermission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.question = question;
        this.answer = answer;
        this.role = role;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.picturePath = picturePath;
        this.introduce = introduce;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.haspermission = haspermission;
    }

    public User(String username, String password, String email, Integer role, Integer haspermission) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.haspermission = haspermission;
    }

    public User(String username, String password, String email, Integer role, String nickname, String introduce, Integer haspermission) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.nickname = nickname;
        this.introduce = introduce;
        this.haspermission = haspermission;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getHaspermission() {
        return haspermission;
    }

    public void setHaspermission(Integer haspermission) {
        this.haspermission = haspermission;
    }
}