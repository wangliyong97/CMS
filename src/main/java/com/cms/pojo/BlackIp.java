package com.cms.pojo;

import java.util.Date;

public class BlackIp {
    private Integer id;

    private Integer vid;

    private String ip;

    private String city;

    private String browsertype;

    private String platformtype;

    private Date time;

    public BlackIp(Integer id, Integer vid, String ip, String city, String browsertype, String platformtype, Date time) {
        this.id = id;
        this.vid = vid;
        this.ip = ip;
        this.city = city;
        this.browsertype = browsertype;
        this.platformtype = platformtype;
        this.time = time;
    }

    public BlackIp() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getBrowsertype() {
        return browsertype;
    }

    public void setBrowsertype(String browsertype) {
        this.browsertype = browsertype == null ? null : browsertype.trim();
    }

    public String getPlatformtype() {
        return platformtype;
    }

    public void setPlatformtype(String platformtype) {
        this.platformtype = platformtype == null ? null : platformtype.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}