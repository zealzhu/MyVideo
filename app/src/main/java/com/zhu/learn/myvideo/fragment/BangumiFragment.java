package com.zhu.learn.myvideo.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhu.learn.myvideo.R;

/**
 * Created by zhu on 2017/3/19.
 */

public class BangumiFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bangumi, container, false);
        return view;
    }
}
