package com.cms.pojo;

import java.util.Date;

public class ActivityType {
    private Integer id;

    private String typename;

    private Integer num;

    private Date addtime;

    public ActivityType(Integer id, String typename, Integer num, Date addtime) {
        this.id = id;
        this.typename = typename;
        this.num = num;
        this.addtime = addtime;
    }

    public ActivityType() {
        super();
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
        this.typename = typename == null ? null : typename.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}