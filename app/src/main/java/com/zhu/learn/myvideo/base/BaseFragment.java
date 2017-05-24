package com.zhu.learn.myvideo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by zhu on 2017/5/23.
 */

public abstract class BaseFragment<V extends BaseView, T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取Present
        mPresenter = initPresent();
        mPresenter.attach((V)this);
    }



    @Override
    public void onResume() {
        mPresenter.attach((V)this);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if(!mPresenter.isAttach())
            mPresenter.attach((V)this);
        super.onDestroy();
    }

    protected abstract T initPresent();
}
