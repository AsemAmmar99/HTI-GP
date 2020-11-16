package com.scorpion_a.htigp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewRequestsListAdapter extends RecyclerView.Adapter<ViewRequestsListAdapter.ViewHolder> {
    private ViewRequestsListData[] requestsdata;

    // RecyclerView recyclerView;
    public ViewRequestsListAdapter(ViewRequestsListData[] requestsdata) {
        this.requestsdata = requestsdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View requestsItem = layoutInflater.inflate(R.layout.view_requests_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(requestsItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ViewRequestsListData viewRequestsListData = requestsdata[position];
        holder.tvRequestTitle.setText(requestsdata[position].getviewRequestsTitle());
        holder.tvRequestStatus.setText(requestsdata[position].getrequestsStatus());
    }


    @Override
    public int getItemCount() {
        return requestsdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRequestTitle;
        public TextView tvRequestStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvRequestTitle = itemView.findViewById(R.id.tvRequestTitle);
            this.tvRequestStatus = itemView.findViewById(R.id.tvRequestStatus);
        }
    }
}