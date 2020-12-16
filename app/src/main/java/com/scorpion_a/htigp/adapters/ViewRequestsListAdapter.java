package com.scorpion_a.htigp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.htigp.R;
import com.scorpion_a.htigp.model.ViewRequestsListData;

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
        holder.tvRequestNumber.setText(requestsdata[position].getViewRequestsNumber());
        holder.tvRequestDescription.setText(requestsdata[position].getViewRequestsDesc());
        holder.tvRequestStatus.setText(requestsdata[position].getRequestsStatus());
    }


    @Override
    public int getItemCount() {
        return requestsdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRequestNumber;
        public TextView tvRequestDescription;
        public TextView tvRequestStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvRequestNumber = itemView.findViewById(R.id.tvRequestNumber);
            this.tvRequestDescription = itemView.findViewById(R.id.tvRequestDesc);
            this.tvRequestStatus = itemView.findViewById(R.id.tvRequestStatus);
        }
    }
}