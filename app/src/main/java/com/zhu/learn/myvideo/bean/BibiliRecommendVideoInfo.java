package com.zhu.learn.myvideo.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhu on 2017/3/25.
 */

public class BibiliRecommendVideoInfo {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    private int code;
    private String message;
    private List<Data> data;

    public class Data implements Serializable{
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getGo() {
            return go;
        }

        public void setGo(String go) {
            this.go = go;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPlay() {
            return play;
        }

        public void setPlay(String play) {
            this.play = play;
        }

        public String getIdx() {
            return idx;
        }

        public void setIdx(String idx) {
            this.idx = idx;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public Tag getTag() {
            return tag;
        }

        public void setTag(Tag tag) {
            this.tag = tag;
        }

        public List<DislikeReason> getDislike_reasons() {
            return dislike_reasons;
        }

        public void setDislike_reasons(List<DislikeReason> dislike_reasons) {
            this.dislike_reasons = dislike_reasons;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getDanmaku() {
            return danmaku;
        }

        public void setDanmaku(String danmaku) {
            this.danmaku = danmaku;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        private String title;
        private String cover;
        private String uri;
        private String param;
        @SerializedName("goto")
        private String go;
        private String desc;
        private String play;
        private String idx;
        private String tid;
        private String tname;
        private Tag tag;
        private List<DislikeReason> dislike_reasons;
        private String ctime;
        private String danmaku;
        private String duration;
        private String mid;
        private String name;
        private String face;

        public class Tag {
            public String getTagId() {
                return tagId;
            }

            public void setTagId(String tagId) {
                this.tagId = tagId;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            @SerializedName("tag_id")
            private String tagId;
            @SerializedName("tag_name")
            private String tagName;
        }
        public class DislikeReason  implements Serializable{
            public int getReasonId() {
                return reasonId;
            }

            public void setReasonId(int reasonId) {
                this.reasonId = reasonId;
            }

            public String getReasonName() {
                return reasonName;
            }

            public void setReasonName(String reasonName) {
                this.reasonName = reasonName;
            }

            @SerializedName("reason_id")
            private int reasonId;
            @SerializedName("reason_name")
            private String reasonName;
        }
    }
}
