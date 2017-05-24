package com.zhu.learn.myvideo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;


import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by zhu on 2017/3/26.
 */

public class VideoPlayer extends LinearLayout implements View.OnClickListener{

    //region Handler处理码
    private final int VIDEO_START = 0;              //开始播放
    private final int HIDE_SEEKBAR = 1;             //隐藏进度条
    //endregion

    private String url;                             //播放地址

    //region 控件
    private LinearLayout control;                   //进度条
    private TextView playTimeNow;                   //当前进度
    private TextView playTimeTotal;                 //总时长
    private TextView bufferText;                    //缓冲文本
    private ImageButton play;                       //播放按钮
    private SeekBar seekBar;                        //进度条控制器
    private VideoView videoView;                    //播放器
    private SwipeRefreshLayout swipeRefreshLayout;  //缓冲图标
    private TextView speed;                         //缓冲速度
    private ImageButton changeScreen;               //全屏切换
    //endregion

    //region 状态
    private boolean isPlay;                         //是否正在播放
    private boolean isComplete;                     //是否播放完成
    private boolean isFullScreen;                   //是否全屏
    private boolean isError;                        //是否播发出错

    public boolean isFullScreen() {
        return isFullScreen;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_screen:
                changeScreen();
                break;
            case R.id.play:
                if(isPlay)
                    pause();
                else
                    play();
                break;
            default:
                break;
        }
    }
    //endregion

    //region 接口

    /**
     * 屏幕改变接口
     */
    public interface OnChangeScreenListener {
        void onFullScreen();
        void onSmallScreen();
    }

    /**
     * 错误接口
     */
    public interface OnErrorListener {
        void onError(String error);
    }

    //endregion

    //region 监听器

    /**
     * 屏幕改变监听器
     */
    private OnChangeScreenListener changeScreenListener;
    public void setChangeScreenListener(OnChangeScreenListener changeScreenListener) {
        this.changeScreenListener = changeScreenListener;
    }

    /**
     * 错误监听器
     */
    private OnErrorListener errorListener;
    public void serErrorListener(OnErrorListener onErrorListener) {
        this.errorListener = onErrorListener;
    }

    /**
     * 进度条控制监听器
     */
    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(videoView == null)
                return;
            playTimeNow.setText(getTimeString(seekBar.getProgress()));
            videoView.seekTo(seekBar.getProgress() * 1000);
        }
    };
    //endregion

    //region 线程

    //进度条自增线程
    Runnable seekBarProgressIncrease = new Runnable() {
        @Override
        public void run() {
            int currentPosition = (int)videoView.getCurrentPosition() / 1000;
            seekBar.setProgress(currentPosition);
            playTimeNow.setText(getTimeString(currentPosition));
            handler.postDelayed(seekBarProgressIncrease, 1000);
        }
    };

    //隐藏进度条线程
    Runnable hideSeekbar = new Runnable() {
        @Override
        public void run() {
            control.setVisibility(GONE);
            if(isFullScreen)
                if(mOnScreenTouchListener != null)
                    mOnScreenTouchListener.onHide();
        }
    };

    //线程处理
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case VIDEO_START:
                    postDelayed(seekBarProgressIncrease, 1000);
                    break;
                case HIDE_SEEKBAR:
                    handler.postDelayed(hideSeekbar, 3000);
                    break;
                default:
                    break;
            }
        }
    };
    //endregion

    //region 将时长转换为文本
    /**
     * 将时长转换为文本
     * @param time 总时长
     * @return 时长文本
     */
    private String getTimeString(int time) {
        int hour = time / 60 / 60;
        int min = time / 60;
        int sec = time % 60;
        return hour == 0 ? String.format(Locale.SIMPLIFIED_CHINESE, "%02d:%02d", min, sec)
                : String.format(Locale.SIMPLIFIED_CHINESE, "%02d:%02d", hour, min);
    }
    //endregion

    //region 播放器监听器
    /**
     * 初始化播放器
     */
    private void initVideoView() {
        isComplete = false;
        videoView.setBufferSize(1024 * 1024);
        videoView.setOnPreparedListener(preparedListener);
        videoView.setOnErrorListener(playerErrorListener);
        videoView.setOnCompletionListener(completionListener);
        videoView.setOnBufferingUpdateListener(bufferingUpdateListener);
        videoView.setOnInfoListener(infoListener);
        videoView.setOnTouchListener(videoViewOnTouchListener);
    }

    /**
     * 加载完毕
     */
    private MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            swipeRefreshLayout.setRefreshing(false);
            //设置时长
            int time = (int)mp.getDuration() / 1000;
            playTimeNow.setText("00:00");
            playTimeTotal.setText(getTimeString(time));
            //设置进度条
            seekBar.setMax(time);
            seekBar.setProgress(0);
            //自动隐藏进度条
            Message message = new Message();
            message.what = HIDE_SEEKBAR;
            handler.sendMessage(message);
            if(isComplete) {
                isComplete = false;
                play();
            }
            if(isError)
                isError = false;
        }
    };
    /**
     * 错误监听器
     */
    private MediaPlayer.OnErrorListener playerErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            if(errorListener != null)
                errorListener.onError("播放错误(what:" + what + " extra:" + extra);
            isError = true;
            pause();
            swipeRefreshLayout.setRefreshing(false);
            return false;
        }
    };
    /**
     * 播放完毕监听器
     */
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //重新加载用于下次继续播放
            isComplete = true;
            pause();
            mp.reset();
        }
    };
    /**
     * 缓冲进度监听器
     */
    private MediaPlayer.OnBufferingUpdateListener bufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            Log.d("VideoPlayer", "缓冲:" + String.valueOf(percent) + "%");
            bufferText.setText("缓冲 " + String.valueOf(percent) + "%");
        }
    };
    /**
     * 播放各种信息监听器
     */
    private MediaPlayer.OnInfoListener infoListener = new MediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            switch (what) {
                case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    //缓冲结束
                    beginProgressIncrease();
                    swipeRefreshLayout.setRefreshing(false);
                    bufferText.setVisibility(GONE);
                    speed.setVisibility(GONE);
                    if(isComplete)
                        return false;   //播放完成
                    play();
                    break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                    //缓冲开始
                    stopProgressIncrease();
                    swipeRefreshLayout.setRefreshing(true);
                    bufferText.setVisibility(VISIBLE);
                    speed.setVisibility(VISIBLE);
                    break;
                case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                    //缓冲速度
                    speed.setText(extra + " KB/s");
                    break;
                default:
                    break;
            }

            return false;
        }
    };
    /**
     * 单击显示隐藏控制条
     */
    private OnTouchListener videoViewOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(control.getVisibility() == VISIBLE) {
                //可见变不可见
                control.setVisibility(GONE);
                if(isFullScreen)
                    if(mOnScreenTouchListener != null)
                        mOnScreenTouchListener.onHide();        //全屏情况下隐藏toolbar
                handler.removeCallbacks(hideSeekbar);
            }
            else {
                //不可见变可见
                control.setVisibility(VISIBLE);
                if(mOnScreenTouchListener != null)
                    mOnScreenTouchListener.onShow();
                Message message = new Message();
                message.what = HIDE_SEEKBAR;
                handler.sendMessage(message);
            }
            return false;
        }
    };
    //endregion

    //region 构造函数
    /**
     * 构造函数
     * @param context
     * @param attrs
     */
    public VideoPlayer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.video_player, this);
        //必须，初始化
        Vitamio.isInitialized(context);
        //获取控件
        control = (LinearLayout)findViewById(R.id.video_control);
        playTimeNow = (TextView)findViewById(R.id.play_time_now);
        playTimeTotal = (TextView)findViewById(R.id.play_time_total);
        play = (ImageButton)findViewById(R.id.play);
        changeScreen = (ImageButton)findViewById(R.id.change_screen);
        seekBar = (SeekBar)findViewById(R.id.seek_bar);
        videoView = (VideoView)findViewById(R.id.video_view);
        bufferText = (TextView)findViewById(R.id.buffer_text);
        speed = (TextView)findViewById(R.id.speed);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);

        isFullScreen = false;
        isPlay = false;
        isComplete = false;

        //设置切换全屏监听器
        changeScreen.setOnClickListener(this);
        //播放按钮监听器
        play.setOnClickListener(this);
        //设置seek bar监听器
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }
    //endregion

    //region 屏幕切换
    /**
     * 全屏切换
     */
    public void changeScreen() {
        if(changeScreenListener == null)
            return;
        if(!isFullScreen) {
            fullScreen();
        } else {
            smallScreen();
        }
        //重置大小
        videoView.getHolder().setSizeFromLayout();
    }
    private void fullScreen() {
        changeScreenListener.onFullScreen();
        RelativeLayout.LayoutParams layoutParams=
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoView.setLayoutParams(layoutParams);
        isFullScreen = true;
    }
    private void smallScreen() {
        changeScreenListener.onSmallScreen();
        RelativeLayout.LayoutParams layoutParams=
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        videoView.setLayoutParams(layoutParams);
        isFullScreen = false;
    }
    //endregion

    /**
     * 设置播放源
     * @param url
     */
    public void setVideoPath(String url) {
        swipeRefreshLayout.setRefreshing(true);
        this.url = url;
        videoView.setVideoPath(url);
        initVideoView();
    }

    /**
     * 播放
     */
    public void play() {
        if(videoView == null || videoView.isPlaying())
            return;
        if(isComplete || isError) {
            videoView.setVideoPath(url);
            return;
        }
        videoView.start();
        play.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bili_player_play_can_pause, null));
        isPlay = true;
        beginProgressIncrease();
    }

    /**
     * 暂停
     */
    public void pause() {
        if(videoView == null)
            return;
        if(videoView.isPlaying() && !isComplete)
            videoView.pause();
        stopProgressIncrease();
        play.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.bili_player_play_can_play, null));
        isPlay = false;
    }

    /**
     * 释放
     */
    public void release() {
        if(videoView == null)
            return;
        pause();
        videoView.stopPlayback();
    }

    /**
     * 控制进度条是否自增
     */
    private void beginProgressIncrease() {
        Message message = new Message();
        message.what = VIDEO_START;
        handler.sendMessage(message);
    }
    private void stopProgressIncrease() {
        handler.removeCallbacks(seekBarProgressIncrease);
    }



    /**
     * 触摸监听器
     */
    public interface OnScreenTouchListener {
        void onHide();
        void onShow();
    }

    public OnScreenTouchListener mOnScreenTouchListener;
    public void setOnScreenTouchListener(OnScreenTouchListener onScreenTouchListener) {
        mOnScreenTouchListener = onScreenTouchListener;
    }

}
