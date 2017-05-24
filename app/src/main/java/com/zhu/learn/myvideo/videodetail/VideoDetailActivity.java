package com.zhu.learn.myvideo.videodetail;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhu.learn.myvideo.base.BaseActivity;
import com.zhu.learn.myvideo.R;
import com.zhu.learn.myvideo.VideoPlayFragment;
import com.zhu.learn.myvideo.adapt.PagerViewFragmentAdapt;
import com.zhu.learn.myvideo.bean.BibiliRecommendVideoInfo;
import com.zhu.learn.myvideo.bean.BilibiliDetailInfo;
import com.zhu.learn.myvideo.fragment.IntroductionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu on 2017/3/26.
 */

public class VideoDetailActivity extends BaseActivity<VideoDetailContract.View, VideoDetailContract.Presenter>
        implements View.OnClickListener, VideoDetailContract.View{
    private Toolbar toolbar;
    private TextView play;
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BilibiliDetailInfo data;
    private ImageView imageView;
    private FloatingActionButton playButton;
    private FrameLayout container;
    private VideoPlayFragment videoPlayFragment;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        play = (TextView)findViewById(R.id.play_now);
        appBarLayout = (AppBarLayout)findViewById(R.id.app_bar_layout);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        imageView = (ImageView)findViewById(R.id.image);
        playButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        container = (FrameLayout)findViewById(R.id.container);

        data = (BilibiliDetailInfo) getIntent().getSerializableExtra("detailInfo");
        init();
    }

    @Override
    protected VideoDetailContract.Presenter initPresent() {
        return new VideoDetailPresenter();
    }

    /**
     * 初始化界面
     */
    public void init() {
        setToolbar();       //设置toolbar
        setTablayout();
        play.setOnClickListener(this);
        //设置Appbar关闭展开事件
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset == 0 || Math.abs(verticalOffset) <= appBarLayout.getTotalScrollRange() / 2) {
                    //展开
                    play.setVisibility(View.GONE);
                } else if(Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //关闭
                    play.setVisibility(View.VISIBLE);
                } else  if(Math.abs(verticalOffset) < appBarLayout.getTotalScrollRange()
                        && Math.abs(verticalOffset) > appBarLayout.getTotalScrollRange() / 2) {
                    //滑动
                    play.setVisibility(View.VISIBLE);
                }
            }
        });
        //加载图片
        Glide.with(this).load(data.getCover()).centerCrop().into(imageView);
        playButton.setOnClickListener(this);
    }

    /**
     * 设置toolbar
     */
    public void setToolbar() {
        //设置返回按钮
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 设置tabLayout
     */
    public void setTablayout() {
        List<Fragment> fragmentList = new ArrayList<>();
        IntroductionFragment fragment = new IntroductionFragment();
        fragment.setData(data);
        fragmentList.add(fragment);
        List<String> names = new ArrayList<>();
        names.add("简介");
        PagerViewFragmentAdapt adapt = new PagerViewFragmentAdapt(getSupportFragmentManager(), fragmentList, names);
        viewPager.setAdapter(adapt);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 设置播放按钮Visible
     * @param visible
     */
    public void setVisible(boolean visible) {
        if(visible) {
            imageView.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
            playButton.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.video_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_now:
            case R.id.floatingActionButton:
                mPresenter.playVideo(data.getAid());
                break;
            default:
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if(videoPlayFragment == null) {
            super.onBackPressed();
            return;
        }

        if(videoPlayFragment.isFullScreen())
            videoPlayFragment.changScreen();
        else
            super.onBackPressed();
    }

    /**
     * 显示播放fragment
     * @param url 视频信息url
     */
    @Override
    public void showPlayVideoFragment(String url) {
        //初始化Fragment
        videoPlayFragment = new VideoPlayFragment();
        videoPlayFragment.setUrl(url);
        videoPlayFragment.setToolbar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, videoPlayFragment);
        transaction.addToBackStack(null);       //添加到返回栈
        transaction.commit();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setVisible(false);
            }
        });
    }

    /**
     * 全屏
     */
    @Override
    public void fullScreen() {
        //隐藏状态栏和导航栏
        if(Build.VERSION.SDK_INT >= 21) {
            //5.0以上版本
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        //设置屏幕方向全屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        viewPager.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
        //改变父布局宽度高度
        CollapsingToolbarLayout.LayoutParams params =
                new CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.MATCH_PARENT);
        container.setLayoutParams(params);
        CoordinatorLayout.LayoutParams layoutParams =
                new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT);
        appBarLayout.setLayoutParams(layoutParams);
        //设置AppBarLayout不滑动
        AppBarLayout.LayoutParams ablParam =
                new AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.WRAP_CONTENT);
        ablParam.setScrollFlags(0);
        findViewById(R.id.collapsing_toolbar_layout).setLayoutParams(ablParam);
        toolbar.setFitsSystemWindows(true);
    }

    /**
     * 半屏
     */
    @Override
    public void smallScreen() {
        //显示状态栏和导航栏
        if(Build.VERSION.SDK_INT >= 21) {
            //5.0以上版本
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        //改变父布局参数
        CollapsingToolbarLayout.LayoutParams containerParams =
                new CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, (int)(250 * getResources().getDisplayMetrics().density));
        container.setLayoutParams(containerParams);
        CoordinatorLayout.LayoutParams appBarLayoutParams =
                new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        appBarLayout.setLayoutParams(appBarLayoutParams);
        //设置AppBarLayout可以滑动
        AppBarLayout.LayoutParams ablParam =
                new AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.WRAP_CONTENT);
        ablParam.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL| AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        findViewById(R.id.collapsing_toolbar_layout).setLayoutParams(ablParam);
        toolbar.setFitsSystemWindows(false);
    }

    /**
     * 显示对话框
     * @param msg
     */
    @Override
    public void showDialog(String msg) {
        if(mProgressDialog == null)
            mProgressDialog = new ProgressDialog(VideoDetailActivity.this);
        mProgressDialog.setTitle("提示");
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 隐藏对话框
     */
    @Override
    public void hideDialog() {
        if(mProgressDialog == null)
            return;
        mProgressDialog.dismiss();
    }

    /**
     * 显示错误信息
     * @param error
     */
    @Override
    public void showErrorToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
