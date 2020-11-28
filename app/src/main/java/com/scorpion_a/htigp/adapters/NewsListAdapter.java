package com.scorpion_a.htigp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.htigp.activities.NewsDetailsActivity;
import com.scorpion_a.htigp.activities.NotificationDetailsActivity;
import com.scorpion_a.htigp.model.NewsListData;
import com.scorpion_a.htigp.R;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    private NewsListData[] newsdata;
    private Context context;

    // RecyclerView recyclerView;
    public NewsListAdapter(NewsListData[] newsdata, Context context) {
        this.newsdata = newsdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View newsItem = layoutInflater.inflate(R.layout.news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(newsItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NewsListData myListData = newsdata[position];
        holder.tvTitle.setText(newsdata[position].getnewsTitle());
        holder.tvDesc.setText(newsdata[position].getnewsDesc());
        holder.clNewsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NewsDetailsActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDesc;
        public ConstraintLayout clNewsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvNewsTitle);
            this.tvDesc = itemView.findViewById(R.id.tvNewsDesc);
            this.clNewsItem = itemView.findViewById(R.id.clNewsItem);
        }
    }
}