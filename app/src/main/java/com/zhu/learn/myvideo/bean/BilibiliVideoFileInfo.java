package com.zhu.learn.myvideo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhu on 2017/3/26.
 * 视频文件信息
 */

public class BilibiliVideoFileInfo {
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getTimelength() {
        return timelength;
    }

    public void setTimelength(long timelength) {
        this.timelength = timelength;
    }

    public String getAcceptFormat() {
        return acceptFormat;
    }

    public void setAcceptFormat(String acceptFormat) {
        this.acceptFormat = acceptFormat;
    }

    public String getSeekParam() {
        return seekParam;
    }

    public void setSeekParam(String seekParam) {
        this.seekParam = seekParam;
    }

    public String getSeekType() {
        return seekType;
    }

    public void setSeekType(String seekType) {
        this.seekType = seekType;
    }

    public List<Durl> getDurls() {
        return durls;
    }

    public void setDurls(List<Durl> durls) {
        this.durls = durls;
    }

    private String from;
    private String result;
    private String format;
    private long timelength;
    @SerializedName("accept_format")
    private String acceptFormat;

    public String getAcceptQuality() {
        return acceptQuality;
    }

    public void setAcceptQuality(String acceptQuality) {
        this.acceptQuality = acceptQuality;
    }

    private String acceptQuality;
    @SerializedName("seek_param")
    private String seekParam;
    @SerializedName("seek_type")
    private String seekType;
    @SerializedName("durl")
    private List<Durl> durls;

    public class Durl {
        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public long getLength() {
            return length;
        }

        public void setLength(long length) {
            this.length = length;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getBackupUrl() {
            return backupUrl;
        }

        public void setBackupUrl(List<String> backupUrl) {
            this.backupUrl = backupUrl;
        }

        private String order;
        private long length;
        private long size;
        private String url;
        @SerializedName("backup_url")
        private List<String> backupUrl;
    }

}
