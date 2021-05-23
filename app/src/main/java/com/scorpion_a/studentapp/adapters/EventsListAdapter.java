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

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.activities.NewsDetailsActivity;
import com.scorpion_a.studentapp.model.ArticlesListData;
import com.scorpion_a.studentapp.model.EventsListData;
import com.scorpion_a.studentapp.model.NewsListData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {
    private ArrayList<ArticlesListData> eventsdata;
    private Context context;

    // RecyclerView recyclerView;
    public EventsListAdapter(ArrayList<ArticlesListData>eventsdata, Context context) {
        this.eventsdata = eventsdata;
        this.context = context;
    }

    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View newsItem = layoutInflater.inflate(R.layout.item_events, parent, false);
        EventsListAdapter.ViewHolder viewHolder = new EventsListAdapter.ViewHolder(newsItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventsListAdapter.ViewHolder holder, int position) {
        final ArticlesListData myListData = eventsdata.get(position);
        holder.tvTitle.setText(eventsdata.get(position).getTitle());
        holder.tvData.setText(eventsdata.get(position).getDate());
        Picasso.with(context).load("https://app.jabbarproject.com/"+eventsdata.get(position).getImages()).fit().into( holder.ivImage);
        holder.clEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetailsActivity.class);
                intent.putExtra("id",eventsdata.get(position).getId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return eventsdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvData;
        public ImageView ivImage;
        public ConstraintLayout clEvents;

//        public ConstraintLayout clNewsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvEventsTitle);
            this.tvData = itemView.findViewById(R.id.tvEventDate);
            this.ivImage = itemView.findViewById(R.id.ivEvent);
            this.clEvents = itemView.findViewById(R.id.clEventsItem);
        }
    }
}
