package com.cms.pojo;

import java.util.Date;

public class ActivityType {
    private Integer id;

    private String typename;

    private Integer num;

    private Date addTime;

    public ActivityType(Integer id, String typename, Integer num, Date addTime) {
        this.id = id;
        this.typename = typename;
        this.num = num;
        this.addTime = addTime;
    }

    public ActivityType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}