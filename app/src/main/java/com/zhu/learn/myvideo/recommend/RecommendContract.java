package com.zhu.learn.myvideo.recommend;

import com.zhu.learn.myvideo.base.BasePresenter;
import com.zhu.learn.myvideo.base.BaseView;
import com.zhu.learn.myvideo.bean.BibiliRecommendVideoInfo;

import java.util.List;

/**
 * Created by zhu on 2017/5/22.
 */

interface RecommendContract {
    abstract class Presenter extends BasePresenter<View> {
        abstract void loadData();
    }
    interface View extends BaseView {
        void showInfo(List<BibiliRecommendVideoInfo.Data> dataList);

        void showRefresh();

        void hideRefresh();

        void notifyList();
    }
}
