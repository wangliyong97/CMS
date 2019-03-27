package com.cms.pojo;

import java.util.Date;

public class Subscribe {
    private Integer id;

    private Integer userId;

    private Integer activityId;

    private Integer status;

    private Date addTime;

    private Date reminderTime;

    public Subscribe(Integer id, Integer userId, Integer activityId, Integer status, Date addTime, Date reminderTime) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.status = status;
        this.addTime = addTime;
        this.reminderTime = reminderTime;
    }

    public Subscribe(Integer userId, Integer activityId, Integer status, Date addTime, Date reminderTime) {
        this.userId = userId;
        this.activityId = activityId;
        this.status = status;
        this.addTime = addTime;
        this.reminderTime = reminderTime;
    }

    public Subscribe() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Date reminderTime) {
        this.reminderTime = reminderTime;
    }
}