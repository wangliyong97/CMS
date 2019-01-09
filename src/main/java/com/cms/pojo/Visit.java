package com.cms.pojo;

import java.util.Date;

public class Visit {
    private Integer id;

    private String ip;

    private String useragent;

    private String city;

    private String url;

    private String browsertype;

    private String platformtype;

    private Date time;

    public Visit(Integer id, String ip, String useragent, String city, String url, String browsertype, String platformtype, Date time) {
        this.id = id;
        this.ip = ip;
        this.useragent = useragent;
        this.city = city;
        this.url = url;
        this.browsertype = browsertype;
        this.platformtype = platformtype;
        this.time = time;
    }

    public Visit() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent == null ? null : useragent.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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