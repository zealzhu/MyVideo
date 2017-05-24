package com.zhu.learn.myvideo.model;

import com.zhu.learn.myvideo.bean.BilibiliSearchVideoInfo;
import com.zhu.learn.myvideo.bean.BilibiliVideoFileInfo;
import com.zhu.learn.myvideo.util.HttpUtil;
import com.zhu.learn.myvideo.util.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhu on 2017/5/23.
 */

public class BilibiliSearchVideoInfoModel {

    public BilibiliSearchVideoInfoModel() {

    }

    public void getSearchVideoInfo(String name, int page, final OnComplete onComplete) {
        String url = makeUrl(name, page);
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onComplete.OnError("网络错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                BilibiliSearchVideoInfo searchVideoInfo = Utility.handleSearchVideoInfo(responseString);
                if(searchVideoInfo == null) {
                    onComplete.OnError("加载出错");
                    return;
                }

                if(searchVideoInfo.getCode() != 0) {
                    onComplete.OnError(searchVideoInfo.getMessage());
                    return;
                }

                onComplete.OnSuccess(searchVideoInfo.getData().getItems(), searchVideoInfo.getData().getPage());
            }
        });
    }
    /**
     *  构造URL
     * @param name 搜索名字
     * @param page 页码
     */
    public String makeUrl(String name, int page) {
        return "http://app.bilibili.com/x/v2/search?" +
                "access_key=9c342000cd7c353a9b5bd27f03e0028d" +
                "&appkey=1d8b6e7d45233436&build=504001" +
                "&duration=0" +
                "&keyword=" + name +
                "&mobi_app=android" +
                "&platform=android" +
                "&pn=" + page +
                "&ps=20" +
                "&ts=1495550339" +
                "&sign=4640b012ccd74ab1363cb94e756c258f";
    }
    public interface OnComplete {
        void OnSuccess(BilibiliSearchVideoInfo.Data.Items item, int page);
        void OnError(String error);
    }
}
