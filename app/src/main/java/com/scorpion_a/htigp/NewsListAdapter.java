package com.scorpion_a.htigp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    private NewsListData[] newsdata;

    // RecyclerView recyclerView;
    public NewsListAdapter(NewsListData[] newsdata) {
        this.newsdata = newsdata;
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
    }


    @Override
    public int getItemCount() {
        return newsdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvNewsTitle);
            this.tvDesc = itemView.findViewById(R.id.tvNewsDesc);
        }
    }
}