package com.cms.pojo;

import java.util.Date;

public class Activity {
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

    private Date updatetime;

    private Date addtime;

    private Integer status;

    private Integer typeId;

    private String content;

    public Activity(Integer id, String title, String introduction, String keyword, String images, Integer clicknum, Integer commentnum, Integer agreenum, Integer istop, Integer isrecommend, Date updatetime, Date addtime, Integer status, Integer typeId, String content) {
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
        this.typeId = typeId;
        this.content = content;
    }

    public Activity() {
        super();
    }

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
        this.title = title == null ? null : title.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}