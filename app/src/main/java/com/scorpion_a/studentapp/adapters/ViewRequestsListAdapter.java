package com.scorpion_a.studentapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.model.ViewRequestsListData;

import java.util.List;

public class ViewRequestsListAdapter extends RecyclerView.Adapter<ViewRequestsListAdapter.ViewHolder> {
    private List< ViewRequestsListData> requestsdata;

    // RecyclerView recyclerView;
    public ViewRequestsListAdapter(List< ViewRequestsListData> requestsdata) {
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
        final ViewRequestsListData viewRequestsListData = requestsdata.get(position);
        holder.tvRequestNumber.setText(viewRequestsListData.getId());
        holder.tvRequestDescription.setText(viewRequestsListData.getName());
        holder.tvRequestStatus.setText(viewRequestsListData.getPrice());
//        holder.tvRequestCount.setText(requestsdata[position].getRequestsCount());
//        holder.tvRequestTime.setText(requestsdata[position].getRequestsTime());
    }


    @Override
    public int getItemCount() {
        return requestsdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRequestNumber;
        public TextView tvRequestDescription;
        public TextView tvRequestStatus;
        public TextView tvRequestCount;
        public TextView tvRequestTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvRequestNumber = itemView.findViewById(R.id.tvRequestNumber);
            this.tvRequestDescription = itemView.findViewById(R.id.tvRequestDesc);
            this.tvRequestStatus = itemView.findViewById(R.id.tvRequestStatus);
            this.tvRequestCount = itemView.findViewById(R.id.tvRequestCount);
            this.tvRequestTime = itemView.findViewById(R.id.tvRequestTime);
        }
    }
}