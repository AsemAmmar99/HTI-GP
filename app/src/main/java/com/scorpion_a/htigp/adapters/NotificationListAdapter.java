package com.scorpion_a.htigp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.htigp.model.NotificationListData;
import com.scorpion_a.htigp.R;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {
    private NotificationListData[] notificationdata;

    // RecyclerView recyclerView;
    public NotificationListAdapter(NotificationListData[] notificationdata) {
        this.notificationdata = notificationdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View notificationItem = layoutInflater.inflate(R.layout.notification_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(notificationItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NotificationListData notificationListData = notificationdata[position];
        holder.tvTitle.setText(notificationListData.getnotificationTitle());
        holder.tvDesc.setText(notificationListData.getnotificationDesc());
        holder.tvTime.setText(notificationListData.getnotificationTime());
    }


    @Override
    public int getItemCount() {
        return notificationdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDesc;
        public TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvNotifTitle);
            this.tvDesc = itemView.findViewById(R.id.tvNotifDesc);
            this.tvTime = itemView.findViewById(R.id.tvNotifTime);
        }
    }
}
