package com.zhu.learn.myvideo.search.bilibili;

import com.zhu.learn.myvideo.base.BasePresenter;
import com.zhu.learn.myvideo.bean.BilibiliSearchVideoInfo;
import com.zhu.learn.myvideo.search.BaseSearchView;

/**
 * Created by zhu on 2017/5/23.
 */

interface BilibiliContract {
    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadBilibili(String name, int page);
    }

    interface View extends BaseSearchView {
        void showVideoList(BilibiliSearchVideoInfo.Data.Items dataList);
    }
}
