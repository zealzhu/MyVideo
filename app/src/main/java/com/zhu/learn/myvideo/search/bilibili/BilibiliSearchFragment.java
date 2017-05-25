package com.zhu.learn.myvideo.search.bilibili;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhu.learn.myvideo.R;
import com.zhu.learn.myvideo.adapt.BilibiliSearchRecycleAdapter;
import com.zhu.learn.myvideo.base.BaseFragment;
import com.zhu.learn.myvideo.bean.BilibiliSearchVideoInfo;

import java.util.ArrayList;
import java.util.List;


public class BilibiliSearchFragment extends BaseFragment<BilibiliContract.View, BilibiliContract.Presenter>
        implements BilibiliContract.View {

    private BilibiliSearchRecycleAdapter adapt;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<BilibiliSearchVideoInfo.Data.Items.Archive> list;
    private String name;

    public BilibiliSearchFragment() {
        // Required empty public constructor
    }


    @Override
    protected BilibiliContract.Presenter initPresent() {
        return new BilibiliPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bilibili_search, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recommend_recycle_view);
        //设置适配器
        list = new ArrayList<>();
        adapt = new BilibiliSearchRecycleAdapter(getContext(), list);
        recyclerView.setAdapter(adapt);
        //设置网格布局管理器
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(manager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.loadBilibili(name, 1);
    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showVideoList(BilibiliSearchVideoInfo.Data.Items dataList) {
        list.clear();
        list.addAll(dataList.getArchive());
        adapt.notifyDataSetChanged();
    }

    @Override
    public void showRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshList() {
        mPresenter.loadBilibili(name, 1);
    }

    @Override
    public void setSearchName(String name) {
        this.name = name;
    }
}
