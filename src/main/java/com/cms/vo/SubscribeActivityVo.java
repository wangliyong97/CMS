package com.cms.vo;

import com.cms.pojo.ActivityType;
import com.cms.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by wangliyong on 2019/3/28.
 */
public class SubscribeActivityVo {
    private Integer id;

    private String title;

    private String introduction;

    private String keyword;

    private String images;

    private Integer clicknum;

    private Integer commentnum;

    private Integer agreenum;

    private Integer istop;

    private Integer isrecommend;

    /**  */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updatetime;

    /**  */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date addtime;

    private Integer status;

    private ActivityType type;

    private User user;

    private String content;

    /**
     * 订阅用户信息
     */
    private Integer subUserId;  //订阅用户id

    private Integer subscribeStatus;  //订阅信息状态

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date reminderTime; //订阅提醒时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getClicknum() {
        return clicknum;
    }

    public void setClicknum(Integer clicknum) {
        this.clicknum = clicknum;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    public Integer getAgreenum() {
        return agreenum;
    }

    public void setAgreenum(Integer agreenum) {
        this.agreenum = agreenum;
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    public Integer getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(Integer isrecommend) {
        this.isrecommend = isrecommend;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(Integer subUserId) {
        this.subUserId = subUserId;
    }

    public Integer getSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(Integer subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    public Date getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Date reminderTime) {
        this.reminderTime = reminderTime;
    }

    public SubscribeActivityVo() {
    }

    public SubscribeActivityVo(Integer id, Integer subUserId, Integer subscribeStatus, Date reminderTime) {
        this.id = id;
        this.subUserId = subUserId;
        this.subscribeStatus = subscribeStatus;
        this.reminderTime = reminderTime;
    }

    public SubscribeActivityVo(String title, String introduction, String keyword, String images, Integer clicknum, Integer commentnum, Integer agreenum, Integer istop, Integer isrecommend, Date updatetime, Date addtime, Integer status, ActivityType type, User user, String content, Integer subUserId, Integer subscribeStatus, Date reminderTime) {
        this.title = title;
        this.introduction = introduction;
        this.keyword = keyword;
        this.images = images;
        this.clicknum = clicknum;
        this.commentnum = commentnum;
        this.agreenum = agreenum;
        this.istop = istop;
        this.isrecommend = isrecommend;
        this.updatetime = updatetime;
        this.addtime = addtime;
        this.status = status;
        this.type = type;
        this.user = user;
        this.content = content;
        this.subUserId = subUserId;
        this.subscribeStatus = subscribeStatus;
        this.reminderTime = reminderTime;
    }

    public SubscribeActivityVo(Integer id, String title, String introduction, String keyword, String images, Integer clicknum, Integer commentnum, Integer agreenum, Integer istop, Integer isrecommend, Date updatetime, Date addtime, Integer status, ActivityType type, User user, String content, Integer subUserId, Integer subscribeStatus, Date reminderTime) {
        this.id = id;
        this.title = title;
        this.introduction = introduction;
        this.keyword = keyword;
        this.images = images;
        this.clicknum = clicknum;
        this.commentnum = commentnum;
        this.agreenum = agreenum;
        this.istop = istop;
        this.isrecommend = isrecommend;
        this.updatetime = updatetime;
        this.addtime = addtime;
        this.status = status;
        this.type = type;
        this.user = user;
        this.content = content;
        this.subUserId = subUserId;
        this.subscribeStatus = subscribeStatus;
        this.reminderTime = reminderTime;
    }


}
