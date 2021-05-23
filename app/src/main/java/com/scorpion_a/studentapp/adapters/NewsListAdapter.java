package com.scorpion_a.studentapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.activities.NewsDetailsActivity;
import com.scorpion_a.studentapp.model.ArticlesListData;
import com.scorpion_a.studentapp.model.NewsListData;
import com.scorpion_a.studentapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    private ArrayList <ArticlesListData>newsdata;
    private Context context;

    // RecyclerView recyclerView;
    public NewsListAdapter(ArrayList <ArticlesListData>newsdata, Context context) {
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
        final ArticlesListData myListData = newsdata.get(position);
        holder.tvTitle.setText(newsdata.get(position).getTitle());
        holder.tvDate.setText(newsdata.get(position).getDate());
        Picasso.with(context).load("https://app.jabbarproject.com/"+newsdata.get(position).getImages()).fit().into( holder.ivImage);
        holder.clNewsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetailsActivity.class);
                intent.putExtra("id",newsdata.get(position).getId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDate;
        public ImageView ivImage;
        public ConstraintLayout clNewsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvNewsTitle);
            this.tvDate = itemView.findViewById(R.id.tvNewsDate);
            this.ivImage = itemView.findViewById(R.id.ivNews);
            this.clNewsItem = itemView.findViewById(R.id.clNewsItem);
        }
    }
}