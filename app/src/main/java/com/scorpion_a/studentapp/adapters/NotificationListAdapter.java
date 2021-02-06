package com.scorpion_a.studentapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.activities.NotificationDetailsActivity;
import com.scorpion_a.studentapp.model.NotificationListData;
import com.scorpion_a.studentapp.R;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {
    private NotificationListData[] notificationdata;
    private Context context;

    // RecyclerView recyclerView;
    public NotificationListAdapter(NotificationListData[] notificationdata, Context context) {
        this.notificationdata = notificationdata;
        this.context = context;
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
        holder.clNotificationsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NotificationDetailsActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return notificationdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDesc;
        public TextView tvTime;
        public ConstraintLayout clNotificationsItem;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvNotifTitle);
            this.tvDesc = itemView.findViewById(R.id.tvNotifDesc);
            this.tvTime = itemView.findViewById(R.id.tvNotifTime);
            this.clNotificationsItem = itemView.findViewById(R.id.clNotificationsItem);
        }
    }
}
