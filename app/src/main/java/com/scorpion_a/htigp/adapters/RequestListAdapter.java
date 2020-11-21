package com.scorpion_a.htigp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.htigp.R;
import com.scorpion_a.htigp.model.RequestListData;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.ViewHolder> {
    private RequestListData[] requestdata;

    // RecyclerView recyclerView;
    public RequestListAdapter(RequestListData[] requestdata) {
        this.requestdata = requestdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View requestItem = layoutInflater.inflate(R.layout.request_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(requestItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RequestListData requestListData = requestdata[position];
        holder.tvRequest.setText(requestdata[position].getrequestTitle());
    }


    @Override
    public int getItemCount() {
        return requestdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRequest;


        public ViewHolder(View itemView) {
            super(itemView);
            this.tvRequest = itemView.findViewById(R.id.tvRequest);
        }
    }
}
