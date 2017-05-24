package com.zhu.learn.myvideo.search.bilibili;

import android.os.Handler;
import android.os.Looper;

import com.zhu.learn.myvideo.bean.BilibiliSearchVideoInfo;
import com.zhu.learn.myvideo.model.BilibiliSearchVideoInfoModel;

/**
 * Created by zhu on 2017/5/23.
 */

public class BilibiliPresenter extends BilibiliContract.Presenter {
    private Handler mHandler;
    BilibiliPresenter() {
        mHandler = new Handler(Looper.getMainLooper());
    }
    @Override
    public void loadBilibili(String name, int page) {
        mView.showRefresh();
        BilibiliSearchVideoInfoModel model = new BilibiliSearchVideoInfoModel();
        model.getSearchVideoInfo(name, page, new BilibiliSearchVideoInfoModel.OnComplete() {
            @Override
            public void OnSuccess(final BilibiliSearchVideoInfo.Data.Items item, int page) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showVideoList(item);
                        mView.hideRefresh();
                    }
                });
            }

            @Override
            public void OnError(final String error) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideRefresh();
                        mView.showErrorToast(error);
                    }
                });
            }
        });
    }
}
