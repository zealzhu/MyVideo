package com.zhu.learn.myvideo.adapt;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhu on 2017/3/19.
 */

public class PagerViewFragmentAdapt extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;    //每一个tab的碎片
    private List<String> tabNameList;       //每一个tab的标题

    public PagerViewFragmentAdapt(FragmentManager fm, List<Fragment> fragmentList, List<String> tabNameList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tabNameList = tabNameList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabNameList.size();
    }

    //设置tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNameList.get(position);
    }
}
