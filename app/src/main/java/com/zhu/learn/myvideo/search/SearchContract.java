package com.zhu.learn.myvideo.search;

import com.zhu.learn.myvideo.base.BasePresenter;
import com.zhu.learn.myvideo.base.BaseView;
import com.zhu.learn.myvideo.bean.BilibiliSearchVideoInfo;

import java.util.List;

/**
 * Created by zhu on 2017/5/23.
 */

public interface SearchContract {
    abstract class Presenter extends BasePresenter<View> {
        abstract void loadDatas(String name, int page);

    }
    interface View extends BaseView {

    }
}
