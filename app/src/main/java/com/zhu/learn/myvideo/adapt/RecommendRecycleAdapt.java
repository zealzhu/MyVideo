package com.zhu.learn.myvideo.adapt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhu.learn.myvideo.R;
import com.zhu.learn.myvideo.bean.BilibiliDetailInfo;
import com.zhu.learn.myvideo.videodetail.VideoDetailActivity;
import com.zhu.learn.myvideo.bean.BibiliRecommendVideoInfo;

import java.util.List;

/**
 * Created by zhu on 2017/3/19.
 */

public class RecommendRecycleAdapt extends RecyclerView.Adapter<RecommendRecycleAdapt.RecommendViewHolder> {
    private List<BibiliRecommendVideoInfo.Data> list;
    private Context context;
    class RecommendViewHolder extends RecyclerView.ViewHolder{

        View recommendView;
        TextView title;
        TextView category;
        TextView danmaku;
        TextView viewNumber;
        ImageView picture;
        ImageView more;
        RecommendViewHolder(View itemView) {
            super(itemView);
            recommendView = itemView;
            title = (TextView)itemView.findViewById(R.id.recommend_item_video_title);
            category = (TextView)itemView.findViewById(R.id.recommend_item_category);
            danmaku = (TextView)itemView.findViewById(R.id.danmaku_number);
            viewNumber = (TextView)itemView.findViewById(R.id.view_number);
            picture = (ImageView)itemView.findViewById(R.id.recommend_item_picture);
            more = (ImageView)itemView.findViewById(R.id.recommend_item_more);
        }
    }
    public RecommendRecycleAdapt(Context context, List<BibiliRecommendVideoInfo.Data> data) {
        list = data;
        this.context = context;
    }

    @Override
    public RecommendRecycleAdapt.RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend, parent, false);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, final int position) {
        //设置单击事件
        holder.recommendView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoDetailActivity.class);
                BilibiliDetailInfo detailInfo = new BilibiliDetailInfo();
                detailInfo.setAid(list.get(position).getParam());
                detailInfo.setCover(list.get(position).getCover());
                detailInfo.setDanmakuNumber(list.get(position).getDanmaku());
                detailInfo.setPlayNumber(list.get(position).getPlay());
                detailInfo.setDesc(list.get(position).getDesc());
                detailInfo.setTitle(list.get(position).getTitle());
                intent.putExtra("detailInfo", detailInfo);
                context.startActivity(intent);
            }
        });
        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "单击分类", Toast.LENGTH_SHORT).show();
            }
        });
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "单击菜单", Toast.LENGTH_SHORT).show();
            }
        });
        //设置界面信息
        holder.title.setText(list.get(position).getTitle());
        holder.viewNumber.setText(list.get(position).getPlay());
        holder.danmaku.setText(list.get(position).getDanmaku());
        String category = list.get(position).getTname();
        BibiliRecommendVideoInfo.Data.Tag tag = list.get(position).getTag();
        if(tag != null)
            category += " " + tag.getTagName();
        holder.category.setText(category);
        Glide.with(context)
                .load(list.get(position).getCover())
                .centerCrop()
                .into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
