package com.zhu.learn.myvideo.base;

/**
 * Created by zhu on 2017/5/22.
 */

public abstract class BasePresenter<T> {
    protected T mView;
    void attach(T view) {
        mView = view;
    }
    void dettach() {
        mView = null;
    }

    boolean isAttach() {
        return mView != null;
    }
}
