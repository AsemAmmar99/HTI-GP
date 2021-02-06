package com.scorpion_a.studentapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.activities.NewsDetailsActivity;
import com.scorpion_a.studentapp.model.EventsListData;
import com.scorpion_a.studentapp.model.NewsListData;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {
    private EventsListData[] eventsdata;
    private Context context;

    // RecyclerView recyclerView;
    public EventsListAdapter(EventsListData[] eventsdata, Context context) {
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
        final EventsListData myListData = eventsdata[position];
        holder.tvTitle.setText(eventsdata[position].getEventsTitle());
        holder.tvDesc.setText(eventsdata[position].getEventsDesc());
        holder.tvData.setText(eventsdata[position].getEventsDate());
//        holder.clNewsItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context, NewsDetailsActivity.class));
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return eventsdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDesc;
        public TextView tvData;

//        public ConstraintLayout clNewsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvEventsTitle);
            this.tvDesc = itemView.findViewById(R.id.tvEventsDesc);
            this.tvData = itemView.findViewById(R.id.tvEventDate);
//            this.clNewsItem = itemView.findViewById(R.id.clNewsItem);
        }
    }
}
