package com.zhu.learn.myvideo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhu.learn.myvideo.R;
import com.zhu.learn.myvideo.adapt.RecommendRecycleAdapt;

import java.util.ArrayList;
import java.util.List;

public class LiveFragment extends Fragment {

    public LiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live, container, false);
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
//        List<String> list = new ArrayList<>();
//        for(int i = 0; i < 20; i++) {
//            list.add("测试" + i);
//        }
//
//        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        RecommendRecycleAdapt adapter = new RecommendRecycleAdapt(getContext(), list);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(manager);
        return view;
    }
}
