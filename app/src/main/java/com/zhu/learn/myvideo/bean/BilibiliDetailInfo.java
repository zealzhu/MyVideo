package com.zhu.learn.myvideo.bean;

import java.io.Serializable;

/**
 * Created by zhu on 2017/5/23.
 */

public class BilibiliDetailInfo implements Serializable {
    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(String playNumber) {
        this.playNumber = playNumber;
    }

    public String getDanmakuNumber() {
        return danmakuNumber;
    }

    public void setDanmakuNumber(String danmakuNumber) {
        this.danmakuNumber = danmakuNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String aid;
    private String cover;
    private String playNumber;
    private String danmakuNumber;
    private String title;
    private String desc;
}
