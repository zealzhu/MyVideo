package com.zhu.learn.myvideo.videodetail;

import android.os.Handler;
import android.os.Looper;

import com.zhu.learn.myvideo.bean.BilibiliVideoFileInfo;
import com.zhu.learn.myvideo.bean.BilibiliVideoInfo;
import com.zhu.learn.myvideo.model.VideoFileInfoModel;
import com.zhu.learn.myvideo.model.VideoInfoModel;

/**
 * Created by zhu on 2017/5/22.
 */

class VideoDetailPresenter extends VideoDetailContract.Presenter{
    private Handler mHandler;

    VideoDetailPresenter() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void playVideo(String aid) {
        mView.showDialog("解析地址...");
        VideoInfoModel videoInfoModel = new VideoInfoModel(aid);
        videoInfoModel.getVideoFileInfoModel(new VideoInfoModel.OnComplete() {
            @Override
            public void OnSuccess(BilibiliVideoInfo bilibiliVideoInfo) {
                VideoFileInfoModel videoFileInfoModel = new VideoFileInfoModel(bilibiliVideoInfo.getCid());

                videoFileInfoModel.getVideoFileInfoModel(new VideoFileInfoModel.OnComplete() {
                    @Override
                    public void OnSuccess(final BilibiliVideoFileInfo bilibiliVideoFileInfo) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mView.hideDialog();
                                mView.showPlayVideoFragment(bilibiliVideoFileInfo.getDurls().get(0).getUrl());
                            }
                        });
                    }

                    @Override
                    public void OnError(final String error) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mView.hideDialog();
                                mView.showErrorToast(error);
                            }
                        });
                    }
                });
            }

            @Override
            public void OnError(final String error) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showErrorToast(error);
                    }
                });
            }
        });
    }
}
