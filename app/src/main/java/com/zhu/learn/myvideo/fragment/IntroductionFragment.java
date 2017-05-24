package com.zhu.learn.myvideo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhu.learn.myvideo.R;
import com.zhu.learn.myvideo.bean.BibiliRecommendVideoInfo;
import com.zhu.learn.myvideo.bean.BilibiliDetailInfo;

/**
 * Created by zhu on 2017/3/26.
 */

public class IntroductionFragment extends Fragment {

    private BilibiliDetailInfo data;

    public void setData(BilibiliDetailInfo data) {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduction, container, false);
        TextView viewNumber = (TextView)view.findViewById(R.id.view_number);
        TextView danmakuNumber = (TextView)view.findViewById(R.id.danmaku_number);
        TextView title = (TextView)view.findViewById(R.id.introduction_title);
        TextView info = (TextView)view.findViewById(R.id.introduction_info);

        viewNumber.setTextColor(Color.GRAY);
        danmakuNumber.setTextColor(Color.GRAY);
        info.setTextColor(Color.GRAY);

        viewNumber.setText(data.getPlayNumber());
        danmakuNumber.setText(data.getDanmakuNumber());
        title.setText(data.getTitle());
        info.setText(data.getDesc());
        return view;
    }
}
