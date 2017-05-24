package com.zhu.learn.myvideo.search;

import android.os.Looper;

import android.os.Handler;

/**
 * Created by zhu on 2017/5/23.
 */

class SearchPresenter extends SearchContract.Presenter {
    private Handler mHandler;
    SearchPresenter() {
        mHandler = new Handler(Looper.getMainLooper());
    }
    @Override
    void loadDatas(String name, int page) {

    }


}
