package com.zhu.learn.myvideo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhu on 2017/3/28.
 * 视频基本信息，包括CID
 */

public class BilibiliVideoInfo {
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getArcType() {
        return arcType;
    }

    public void setArcType(String arcType) {
        this.arcType = arcType;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getVideoReview() {
        return videoReview;
    }

    public void setVideoReview(String videoReview) {
        this.videoReview = videoReview;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAllowBp() {
        return allowBp;
    }

    public void setAllowBp(int allowBp) {
        this.allowBp = allowBp;
    }

    public int getAllowFeed() {
        return allowFeed;
    }

    public void setAllowFeed(int allowFeed) {
        this.allowFeed = allowFeed;
    }

    public int getAllowDownload() {
        return allowDownload;
    }

    public void setAllowDownload(int allowDownload) {
        this.allowDownload = allowDownload;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getInstantServer() {
        return instantServer;
    }

    public void setInstantServer(String instantServer) {
        this.instantServer = instantServer;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getOffSite() {
        return offSite;
    }

    public void setOffSite(String offSite) {
        this.offSite = offSite;
    }

    @SerializedName("tid")
    private int typeId;
    @SerializedName("typename")
    private String typeName;
    @SerializedName("arctype")
    private String arcType;

    private String play;

    private String review;
    @SerializedName("video_review")
    private String videoReview;

    private String favorites;

    private String title;
    @SerializedName("allow_bp")
    private int allowBp;
    @SerializedName("allow_feed")
    private int allowFeed;
    @SerializedName("allow_download")
    private int allowDownload;

    private String description;

    private String tag;

    private String pic;

    private String author;

    private String mid;

    private String face;

    private int pages;
    @SerializedName("instant_server")
    private String instantServer;

    private long created;
    @SerializedName("created_at")
    private String createdAt;

    private String credit;

    private String coins;
    @SerializedName("spid")
    private String spId;

    private String src;

    private int cid;
    @SerializedName("partname")
    private String partName;

    private String from;

    private String vid;
    @SerializedName("offsite")
    private String offSite;


}

