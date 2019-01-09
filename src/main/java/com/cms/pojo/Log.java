package com.cms.pojo;

import java.util.Date;

public class Log {
    private Integer id;

    private String usertype;

    private String ip;

    private String description;

    private String param;

    private Date addtime;

    public Log(Integer id, String usertype, String ip, String description, String param, Date addtime) {
        this.id = id;
        this.usertype = usertype;
        this.ip = ip;
        this.description = description;
        this.param = param;
        this.addtime = addtime;
    }

    public Log() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}