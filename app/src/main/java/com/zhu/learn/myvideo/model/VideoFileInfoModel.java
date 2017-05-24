package com.zhu.learn.myvideo.model;

import com.zhu.learn.myvideo.Application;
import com.zhu.learn.myvideo.bean.BilibiliVideoFileInfo;
import com.zhu.learn.myvideo.util.HttpUtil;
import com.zhu.learn.myvideo.util.Utility;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhu on 2017/5/22.
 */

public class VideoFileInfoModel {

    private String url;

    public VideoFileInfoModel(int cid) {
        url = "https://interface.bilibili.com/playurl?" +
                "appkey=" + Application.APP_KEY +
                "&cid=" + cid +
                "&sign=" + Utility.getSign(String.valueOf(cid));
    }

    public void getVideoFileInfoModel(final OnComplete onComplete) {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onComplete.OnError("播放错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                BilibiliVideoFileInfo bilibiliVideoFileInfo = Utility.handleVideoFileInfoResponse(inputStream);
                if(bilibiliVideoFileInfo.getResult().equals("error"))
                    onComplete.OnError(bilibiliVideoFileInfo.getResult());
                else
                    onComplete.OnSuccess(bilibiliVideoFileInfo);
            }
        });
    }

    public interface OnComplete {
        void OnSuccess(BilibiliVideoFileInfo bilibiliVideoFileInfo);
        void OnError(String error);
    }
}
