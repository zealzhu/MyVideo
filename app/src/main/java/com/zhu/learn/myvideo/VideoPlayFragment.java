package com.zhu.learn.myvideo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhu.learn.myvideo.videodetail.VideoDetailActivity;

/**
 * Created by zhu on 2017/3/26.
 */

public class VideoPlayFragment extends Fragment{

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }
    public void setToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    private VideoPlayer videoPlayer;
    private VideoDetailActivity mActivity;
    private Toolbar mToolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_play, container, false);
        videoPlayer = (VideoPlayer)view.findViewById(R.id.player);
        videoPlayer.setVideoPath(url);
        videoPlayer.setOnScreenTouchListener(new VideoPlayer.OnScreenTouchListener() {
            @Override
            public void onHide() {
                if(mToolbar == null)
                    return;
                mToolbar.setVisibility(View.GONE);
            }

            @Override
            public void onShow() {
                if(mToolbar == null)
                    return;
                mToolbar.setVisibility(View.VISIBLE);
            }
        });
        videoPlayer.setChangeScreenListener(new VideoPlayer.OnChangeScreenListener() {
            @Override
            public void onFullScreen() {
                mActivity.fullScreen();
            }

            @Override
            public void onSmallScreen() {
                mActivity.smallScreen();
            }
        });
        videoPlayer.serErrorListener(new VideoPlayer.OnErrorListener() {
            @Override
            public void onError(String error) {
                Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();
            }
        });
        mActivity = ((VideoDetailActivity)getActivity());

        return view;
    }

    @Override
    public void onDestroy() {
        videoPlayer.release();
        mActivity.setVisible(true);
        super.onDestroy();
    }

    /**
     * 全屏切换
     */
    public void changScreen() {
        videoPlayer.changeScreen();
        if(videoPlayer.isFullScreen())
            mActivity.fullScreen();
        else
            mActivity.smallScreen();
    }

    /**
     * 是否全屏
     * @return
     */
    public boolean isFullScreen() {
        return videoPlayer.isFullScreen();
    }

}
