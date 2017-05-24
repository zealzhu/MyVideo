package com.zhu.learn.myvideo.recommend;

import android.os.Handler;
import android.os.Looper;

import com.zhu.learn.myvideo.bean.BibiliRecommendVideoInfo;
import com.zhu.learn.myvideo.model.RecommendInfoModel;

import java.util.List;

/**
 * Created by zhu on 2017/5/22.
 */

class RecommendPresenter extends RecommendContract.Presenter {
    private Handler mHandler;

    RecommendPresenter() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadData() {
        mView.showRefresh();
        RecommendInfoModel model = new RecommendInfoModel();
        model.getRecommendInfo(new RecommendInfoModel.OnComplete() {
            @Override
            public void OnSuccess(final List<BibiliRecommendVideoInfo.Data> list) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideRefresh();
                        mView.showInfo(list);
                        mView.notifyList();
                    }
                });
            }

            @Override
            public void OnError(final String error) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showErrorToast(error);
                        mView.hideRefresh();
                    }
                });
            }
        });
    }
}
