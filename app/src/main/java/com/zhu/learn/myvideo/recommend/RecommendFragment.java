package com.zhu.learn.myvideo.recommend;

import android.app.ProgressDialog;
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
import com.zhu.learn.myvideo.adapt.RecommendRecycleAdapt;
import com.zhu.learn.myvideo.base.BaseFragment;
import com.zhu.learn.myvideo.bean.BibiliRecommendVideoInfo;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends BaseFragment<RecommendContract.View, RecommendContract.Presenter>
        implements RecommendContract.View{

    private List<BibiliRecommendVideoInfo.Data> list;
    private RecommendRecycleAdapt adapt;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                mPresenter.loadData();
            }
        });
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recommend_recycle_view);
        //设置适配器
        list = new ArrayList<>();
        adapt = new RecommendRecycleAdapt(getContext(), list);
        recyclerView.setAdapter(adapt);
        //设置网格布局管理器
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(manager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.loadData();
    }

    @Override
    public void showInfo(List<BibiliRecommendVideoInfo.Data> dataList) {
        list.addAll(dataList);
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
    public void notifyList() {
        adapt.notifyDataSetChanged();
    }

    @Override
    public void showErrorToast(final String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected RecommendContract.Presenter initPresent() {
        return new RecommendPresenter();
    }
}
