package com.zhu.learn.myvideo.model;

import com.zhu.learn.myvideo.bean.BibiliRecommendVideoInfo;
import com.zhu.learn.myvideo.util.HttpUtil;
import com.zhu.learn.myvideo.util.Utility;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhu on 2017/5/22.
 */

public class RecommendInfoModel {
    private final String url = "http://app.bilibili.com/x/feed/index?" +
            "access_key=9437975bea519f64b033a1dff2218fab&appkey=1d8b6e7d45233436&build=501000&idx=1489893927" +
            "&mobi_app=android&network=wifi&platform=android&pull=true&style=2&ts=1490426321000&sign=a5bb120db95b0a03b1ef6c68edabc2ae";

    public void getRecommendInfo(final OnComplete callback) {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                callback.OnError("网络错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                List<BibiliRecommendVideoInfo.Data> data = Utility.handleRecommendResponse(body);
                callback.OnSuccess(data);
            }
        });
    }

    public interface OnComplete {
        void OnSuccess(List<BibiliRecommendVideoInfo.Data> list);
        void OnError(String error);
    }
}
