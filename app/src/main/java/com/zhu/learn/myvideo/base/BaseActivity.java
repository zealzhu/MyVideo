package com.zhu.learn.myvideo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhu on 2017/5/23.
 */

public abstract class BaseActivity<V extends BaseView, T extends BasePresenter<V>> extends AppCompatActivity{

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取Present
        mPresenter = initPresent();
        mPresenter.attach((V)this);
    }

    @Override
    protected void onResume() {
        if(!mPresenter.isAttach())
            mPresenter.attach((V)this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter.dettach();
        super.onDestroy();
    }

    protected abstract T initPresent();
}
