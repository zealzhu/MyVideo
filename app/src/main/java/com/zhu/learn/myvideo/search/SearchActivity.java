package com.zhu.learn.myvideo.search;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhu.learn.myvideo.R;
import com.zhu.learn.myvideo.adapt.PagerViewFragmentAdapt;
import com.zhu.learn.myvideo.base.BaseActivity;
import com.zhu.learn.myvideo.bean.BilibiliSearchVideoInfo;
import com.zhu.learn.myvideo.fragment.BangumiFragment;
import com.zhu.learn.myvideo.fragment.CategoryFragment;
import com.zhu.learn.myvideo.fragment.DiscoveryFragment;
import com.zhu.learn.myvideo.fragment.LiveFragment;
import com.zhu.learn.myvideo.recommend.RecommendFragment;
import com.zhu.learn.myvideo.search.bilibili.BilibiliSearchFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<SearchContract.View, SearchContract.Presenter>
            implements SearchContract.View{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        initTabLayout();
    }

    private void initTabLayout() {
        //创建列表
        List<Fragment> fragmentList = new ArrayList<>();
        BilibiliSearchFragment bilibiliSearchFragment = new BilibiliSearchFragment();
        bilibiliSearchFragment.setName(getIntent().getStringExtra("name"));
        bilibiliSearchFragment.setPage(1);
        fragmentList.add(bilibiliSearchFragment);

        //TabLayout标签名字
        List<String> tabNameList = new ArrayList<>();
        tabNameList.add("bilibili");
//        tabNameList.add("优酷");
//        tabNameList.add("土豆");
//        tabNameList.add("爱奇艺");
//        tabNameList.add("腾讯");
        //创建适配器
        PagerViewFragmentAdapt pvfAdapt = new PagerViewFragmentAdapt(getSupportFragmentManager(), fragmentList, tabNameList);
        //设置viewPager的适配器
        viewPager.setAdapter(pvfAdapt);
        //设置滑动模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置TabLayout跟随viewPage滑动
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected SearchContract.Presenter initPresent() {
        return new SearchPresenter();
    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}
