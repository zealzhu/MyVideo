package com.zhu.learn.myvideo;

import io.vov.vitamio.utils.Log;

/**
 * Created by zhu on 2017/5/23.
 */

public class Application extends android.app.Application {
    private static final String TAG = "Application";
    public static String APP_KEY = "1d8b6e7d45233436";
    public static String SECRET = "560c52ccd288fed045859ed18bffd973";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

}
