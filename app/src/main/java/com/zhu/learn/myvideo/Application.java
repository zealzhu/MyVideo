package com.zhu.learn.myvideo;

import android.content.Context;

import io.vov.vitamio.utils.Log;
import tv.danmaku.bili.api.BilibiliUtil;

/**
 * Created by zhu on 2017/5/23.
 */

public class Application extends android.app.Application {
    private static final String TAG = "Application";
    public static String APP_KEY;
    public static String SECRET;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        APP_KEY = BilibiliUtil.getAppKey();
        SECRET = BilibiliUtil.getSecret();
    }

}
