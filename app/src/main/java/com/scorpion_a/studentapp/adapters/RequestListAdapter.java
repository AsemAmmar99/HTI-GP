package com.scorpion_a.studentapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.activities.SendRequestActivity;
import com.scorpion_a.studentapp.fragments.RequestInfoFragment;
import com.scorpion_a.studentapp.model.RequestListData;

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
        holder.tvRequest.setText(requestdata[position].getRequestTitle());
        holder.tvPrice.setText(requestdata[position].getRequestPrice());
        holder.clRequestItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, SendRequestActivity.class));
                RequestInfoFragment requestInfoFragment= RequestInfoFragment.newInstance();
                Bundle bundle= new Bundle();
                bundle.putString("title", requestdata[position].getRequestTitle());
                bundle.putString("price", requestdata[position].getRequestPrice());
                requestInfoFragment.setArguments(bundle);
                requestInfoFragment.show(((AppCompatActivity) context).getSupportFragmentManager(),
                        "Bottom Sheet Dialog Fragment");
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
        public TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvRequest = itemView.findViewById(R.id.tvRequest);
            this.tvPrice = itemView.findViewById(R.id.tvPrice);
            this.clRequestItem = itemView.findViewById(R.id.clRequestItem);
        }
    }
}
