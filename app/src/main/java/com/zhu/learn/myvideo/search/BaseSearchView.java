package com.zhu.learn.myvideo.search;

import com.zhu.learn.myvideo.base.BaseView;
/**
 * Created by zhu on 2017/5/25.
 */

public interface BaseSearchView extends BaseView {
    void showRefresh();
    void hideRefresh();
    void refreshList();
    void setSearchName(String name);
}
