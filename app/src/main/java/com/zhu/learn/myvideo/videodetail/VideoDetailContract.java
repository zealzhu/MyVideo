package com.zhu.learn.myvideo.videodetail;

import com.zhu.learn.myvideo.base.BasePresenter;
import com.zhu.learn.myvideo.base.BaseView;

/**
 * Created by zhu on 2017/5/22.
 */

interface VideoDetailContract {
    abstract class Presenter extends BasePresenter<View> {
        abstract void playVideo(String aid);
    }

    interface View extends BaseView {
        void showPlayVideoFragment(String url);
        void fullScreen();
        void smallScreen();
        void showDialog(String msg);
        void hideDialog();
    }
}
