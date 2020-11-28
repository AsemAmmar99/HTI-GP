package com.scorpion_a.htigp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.htigp.R;
import com.scorpion_a.htigp.activities.NotificationDetailsActivity;
import com.scorpion_a.htigp.activities.SendRequestActivity;
import com.scorpion_a.htigp.model.RequestListData;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.ViewHolder> {
    private RequestListData[] requestdata;
    private Context context;

    // RecyclerView recyclerView;
    public RequestListAdapter(RequestListData[] requestdata, Context context) {
        this.requestdata = requestdata;
        this.context = context;
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
        holder.clRequestItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SendRequestActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return requestdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRequest;
        public ConstraintLayout clRequestItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvRequest = itemView.findViewById(R.id.tvRequest);
            this.clRequestItem = itemView.findViewById(R.id.clRequestItem);
        }
    }
}
