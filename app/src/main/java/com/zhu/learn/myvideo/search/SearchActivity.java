package com.zhu.learn.myvideo.search;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhu.learn.myvideo.R;
import com.zhu.learn.myvideo.adapt.PagerViewFragmentAdapt;
import com.zhu.learn.myvideo.base.BaseActivity;
import com.zhu.learn.myvideo.search.bilibili.BilibiliSearchFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<SearchContract.View, SearchContract.Presenter>
            implements SearchContract.View, View.OnClickListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EditText mSearchText;
    private Button mSearch;
    private ImageView mBack;
    private PagerViewFragmentAdapt mAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        mSearchText = (EditText)findViewById(R.id.search_text);
        mSearch = (Button)findViewById(R.id.search);
        mBack = (ImageView)findViewById(R.id.back);

        mSearch.setFocusable(false);
        mSearch.setOnClickListener(this);
        mBack.setOnClickListener(this);

        initTabLayout();
    }

    private void initTabLayout() {
        //创建列表
        List<Fragment> fragmentList = new ArrayList<>();
        BilibiliSearchFragment bilibiliSearchFragment = new BilibiliSearchFragment();
        bilibiliSearchFragment.setSearchName(getIntent().getStringExtra("name"));
        fragmentList.add(bilibiliSearchFragment);

        //TabLayout标签名字
        List<String> tabNameList = new ArrayList<>();
        tabNameList.add("哔哩哔哩");
//        tabNameList.add("优酷");
//        tabNameList.add("土豆");
//        tabNameList.add("爱奇艺");
//        tabNameList.add("腾讯");
        //创建适配器
        mAdapt = new PagerViewFragmentAdapt(getSupportFragmentManager(), fragmentList, tabNameList);
        //设置viewPager的适配器
        viewPager.setAdapter(mAdapt);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search:
                String name = mSearchText.getText().toString();
                mPresenter.loadDatas(name);
                break;
            default:
                break;
        }
    }

    @Override
    public void notifyFragmentRefresh(String name) {
        //获取当前选中的tab
        BaseSearchView baseSearchView = (BaseSearchView)mAdapt.getItem(tabLayout.getSelectedTabPosition());
        for(int i = 0; i < mAdapt.getCount(); i++) {
            ((BaseSearchView)mAdapt.getItem(tabLayout.getSelectedTabPosition())).setSearchName(name);
        }
        baseSearchView.refreshList();
    }
}
