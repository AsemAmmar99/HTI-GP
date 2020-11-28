package com.scorpion_a.htigp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.htigp.activities.AppTechSupportActivity;
import com.scorpion_a.htigp.activities.FAQSActivity;
import com.scorpion_a.htigp.activities.LoginScreen;
import com.scorpion_a.htigp.activities.PrintoutActivity;
import com.scorpion_a.htigp.activities.ProfilePageActivity;
import com.scorpion_a.htigp.activities.RegProposalActivity;
import com.scorpion_a.htigp.activities.TestYourselfActivity;
import com.scorpion_a.htigp.model.MoreListData;
import com.scorpion_a.htigp.R;

public class MoreListAdapter extends RecyclerView.Adapter<MoreListAdapter.ViewHolder> {
    private MoreListData[] moredata;
    private Context context;

    // RecyclerView recyclerView;
    public MoreListAdapter(MoreListData[] moredata,Context context) {
        this.moredata = moredata;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View moreItem = layoutInflater.inflate(R.layout.more_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(moreItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MoreListData moreListData = moredata[position];
        holder.tvMore.setText(moredata[position].getmoreItemTitle());
        holder.clMoreItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0:
                        context.startActivity(new Intent(context, ProfilePageActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, PrintoutActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, RegProposalActivity.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, TestYourselfActivity.class));
                        break;
                    case 5:
                        context.startActivity(new Intent(context, FAQSActivity.class));
                        break;
                    case 6:
                        context.startActivity(new Intent(context, AppTechSupportActivity.class));
                        break;
                    case 7:
                        context.startActivity(new Intent(context, LoginScreen.class));
                        ((Activity)context).finish();
                        break;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return moredata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMore;
        public ConstraintLayout clMoreItem;


        public ViewHolder(View itemView) {
            super(itemView);
            this.tvMore = itemView.findViewById(R.id.tvMore);
            this.clMoreItem = itemView.findViewById(R.id.clMoreItem);

        }
    }
}
