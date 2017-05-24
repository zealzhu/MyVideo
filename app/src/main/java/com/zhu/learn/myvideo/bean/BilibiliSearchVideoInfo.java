package com.zhu.learn.myvideo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhu on 2017/5/23.
 */

public class BilibiliSearchVideoInfo {
    private int code;

    private Data data;

    private String message;

    private int ttl;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setTtl(int ttl){
        this.ttl = ttl;
    }
    public int getTtl(){
        return this.ttl;
    }

    public class Data
    {
        private String trackid;

        private int page;

        private List<Nav> nav;

        private Items items;

        public void setTrackid(String trackid){
            this.trackid = trackid;
        }
        public String getTrackid(){
            return this.trackid;
        }
        public void setPage(int page){
            this.page = page;
        }
        public int getPage(){
            return this.page;
        }
        public void setNav(List<Nav> nav){
            this.nav = nav;
        }
        public List<Nav> getNav(){
            return this.nav;
        }
        public void setItems(Items items){
            this.items = items;
        }
        public Items getItems(){
            return this.items;
        }

        public class Items
        {
            private List<Movie> movie;

            private List<Archive> archive;

            public void setMovie(List<Movie> movie){
                this.movie = movie;
            }
            public List<Movie> getMovie(){
                return this.movie;
            }
            public void setArchive(List<Archive> archive){
                this.archive = archive;
            }
            public List<Archive> getArchive(){
                return this.archive;
            }
            public class Archive
            {
                private String title;

                private String cover;

                private String uri;

                private String param;

                @SerializedName("goto")
                private String goTo;

                private String play;

                private String danmaku;

                private String author;

                private int started;

                private int total_count;

                private String desc;

                private String duration;

                private int status;

                private int attentions;

                public void setTitle(String title){
                    this.title = title;
                }
                public String getTitle(){
                    return this.title;
                }
                public void setCover(String cover){
                    this.cover = cover;
                }
                public String getCover(){
                    return this.cover;
                }
                public void setUri(String uri){
                    this.uri = uri;
                }
                public String getUri(){
                    return this.uri;
                }
                public void setParam(String param){
                    this.param = param;
                }
                public String getParam(){
                    return this.param;
                }
                public void setGoto(String goTo){
                    this.goTo = goTo;
                }
                public String getGoto(){
                    return this.goTo;
                }
                public void setPlay(String play){
                    this.play = play;
                }
                public String getPlay(){
                    return this.play;
                }
                public void setDanmaku(String danmaku){
                    this.danmaku = danmaku;
                }
                public String getDanmaku(){
                    return this.danmaku;
                }
                public void setAuthor(String author){
                    this.author = author;
                }
                public String getAuthor(){
                    return this.author;
                }
                public void setStarted(int started){
                    this.started = started;
                }
                public int getStarted(){
                    return this.started;
                }
                public void setTotal_count(int total_count){
                    this.total_count = total_count;
                }
                public int getTotal_count(){
                    return this.total_count;
                }
                public void setDesc(String desc){
                    this.desc = desc;
                }
                public String getDesc(){
                    return this.desc;
                }
                public void setDuration(String duration){
                    this.duration = duration;
                }
                public String getDuration(){
                    return this.duration;
                }
                public void setStatus(int status){
                    this.status = status;
                }
                public int getStatus(){
                    return this.status;
                }
                public void setAttentions(int attentions){
                    this.attentions = attentions;
                }
                public int getAttentions(){
                    return this.attentions;
                }
            }

            public class Movie
            {
                private String title;

                private String cover;

                private String uri;

                private String param;

                @SerializedName("goto")
                private String goTo;

                private int started;

                private int total_count;

                private String desc;

                private String screen_date;

                private String area;

                private String cover_mark;

                private String actors;

                private String staff;

                private int length;

                private int status;

                private int attentions;

                public void setTitle(String title){
                    this.title = title;
                }
                public String getTitle(){
                    return this.title;
                }
                public void setCover(String cover){
                    this.cover = cover;
                }
                public String getCover(){
                    return this.cover;
                }
                public void setUri(String uri){
                    this.uri = uri;
                }
                public String getUri(){
                    return this.uri;
                }
                public void setParam(String param){
                    this.param = param;
                }
                public String getParam(){
                    return this.param;
                }
                public void setGoto(String goTo){
                    this.goTo = goTo;
                }
                public String getGoto(){
                    return this.goTo;
                }
                public void setStarted(int started){
                    this.started = started;
                }
                public int getStarted(){
                    return this.started;
                }
                public void setTotal_count(int total_count){
                    this.total_count = total_count;
                }
                public int getTotal_count(){
                    return this.total_count;
                }
                public void setDesc(String desc){
                    this.desc = desc;
                }
                public String getDesc(){
                    return this.desc;
                }
                public void setScreen_date(String screen_date){
                    this.screen_date = screen_date;
                }
                public String getScreen_date(){
                    return this.screen_date;
                }
                public void setArea(String area){
                    this.area = area;
                }
                public String getArea(){
                    return this.area;
                }
                public void setCover_mark(String cover_mark){
                    this.cover_mark = cover_mark;
                }
                public String getCover_mark(){
                    return this.cover_mark;
                }
                public void setActors(String actors){
                    this.actors = actors;
                }
                public String getActors(){
                    return this.actors;
                }
                public void setStaff(String staff){
                    this.staff = staff;
                }
                public String getStaff(){
                    return this.staff;
                }
                public void setLength(int length){
                    this.length = length;
                }
                public int getLength(){
                    return this.length;
                }
                public void setStatus(int status){
                    this.status = status;
                }
                public int getStatus(){
                    return this.status;
                }
                public void setAttentions(int attentions){
                    this.attentions = attentions;
                }
                public int getAttentions(){
                    return this.attentions;
                }
            }
        }

        public class Nav
        {
            private String name;

            private int total;

            private int pages;

            private int type;

            public void setName(String name){
                this.name = name;
            }
            public String getName(){
                return this.name;
            }
            public void setTotal(int total){
                this.total = total;
            }
            public int getTotal(){
                return this.total;
            }
            public void setPages(int pages){
                this.pages = pages;
            }
            public int getPages(){
                return this.pages;
            }
            public void setType(int type){
                this.type = type;
            }
            public int getType(){
                return this.type;
            }
        }
    }
}
