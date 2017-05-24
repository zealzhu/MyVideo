package com.zhu.learn.myvideo;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zhu.learn.myvideo.adapt.PagerViewFragmentAdapt;
import com.zhu.learn.myvideo.fragment.BangumiFragment;
import com.zhu.learn.myvideo.fragment.CategoryFragment;
import com.zhu.learn.myvideo.fragment.DiscoveryFragment;
import com.zhu.learn.myvideo.fragment.LiveFragment;
import com.zhu.learn.myvideo.recommend.RecommendFragment;
import com.zhu.learn.myvideo.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerViewFragmentAdapt pvfAdapt;
    private DrawerLayout drawerLayout;
    private RelativeLayout toolbarContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        toolbarContent = (RelativeLayout)findViewById(R.id.toolbar_content);
        toolbarContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(Gravity.START))
                    drawerLayout.openDrawer(Gravity.START);
            }
        });

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.toolbar_search);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("name", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setSubmitButtonEnabled(true);//设置是否显示搜索按钮
        searchView.setQueryHint("搜索视频");//设置提示信息
        searchView.setIconifiedByDefault(true);//设置搜索默认为图标
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 初始化控件
     */
    public void init() {
        initToolbar();
        initTablayout();
    }

    /**
     * 初始化Toolbar
     */
    public void initToolbar() {
        //toolbar.setNavigationIcon(R.drawable.ic_drawer_home);
        setSupportActionBar(toolbar);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
//        toggle.syncState();
    }

    /**
     * 初始化Tablayout
     */
    public void initTablayout() {
        //创建列表
        List<Fragment> fragmentList = new ArrayList<>();
        LiveFragment liveFragment = new LiveFragment();
        fragmentList.add(liveFragment);
        RecommendFragment recommendFragment = new RecommendFragment();
        fragmentList.add(recommendFragment);
        BangumiFragment bangumiFragment = new BangumiFragment();
        fragmentList.add(bangumiFragment);
        CategoryFragment categoryFragment = new CategoryFragment();
        fragmentList.add(categoryFragment);
        DiscoveryFragment discoveryFragment = new DiscoveryFragment();
        fragmentList.add(discoveryFragment);

        //TabLayout标签名字
        List<String> tabNameList = new ArrayList<>();
        tabNameList.add("直播");
        tabNameList.add("推荐");
        tabNameList.add("追番");
        tabNameList.add("分区");
        tabNameList.add("发现");
        //创建适配器
        pvfAdapt = new PagerViewFragmentAdapt(getSupportFragmentManager(), fragmentList, tabNameList);
        //设置viewPager的适配器
        viewPager.setAdapter(pvfAdapt);
        //设置滑动模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(Color.GRAY, Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        //设置TabLayout跟随viewPage滑动
        tabLayout.setupWithViewPager(viewPager);
        //设置推荐为选中tab
        tabLayout.getTabAt(1).select();
    }



}
