package com.zhu.learn.myvideo.search.bilibili;

import com.zhu.learn.myvideo.base.BasePresenter;
import com.zhu.learn.myvideo.base.BaseView;
import com.zhu.learn.myvideo.bean.BilibiliSearchVideoInfo;

/**
 * Created by zhu on 2017/5/23.
 */

interface BilibiliContract {
    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadBilibili(String name, int page);
    }

    interface View extends BaseView {
        void showVideoList(BilibiliSearchVideoInfo.Data.Items dataList);
        void showRefresh();
        void hideRefresh();
    }
}
