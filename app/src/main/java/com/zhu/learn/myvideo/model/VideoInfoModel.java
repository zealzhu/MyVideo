package com.zhu.learn.myvideo.model;

import com.zhu.learn.myvideo.bean.BilibiliVideoInfo;
import com.zhu.learn.myvideo.util.HttpUtil;
import com.zhu.learn.myvideo.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhu on 2017/5/22.
 */

public class VideoInfoModel {
    private String url;

    public VideoInfoModel(String aid) {
        url = "http://api.bilibili.com/view?type=json&appkey=8e9fc618fbd41e28&id="
                + aid
                + "&page=1";
    }

    public void getVideoFileInfoModel(final OnComplete onComplete) {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onComplete.OnError("网络错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                BilibiliVideoInfo bilibiliVideoInfo = Utility.handleVideInfoResponse(response.body().string());
                onComplete.OnSuccess(bilibiliVideoInfo);
            }
        });
    }

    public interface OnComplete {
        void OnSuccess(BilibiliVideoInfo bilibiliVideoInfo);

        void OnError(String error);
    }
}
