package com.scorpion_a.studentapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.activities.AboutHTIActivity;
import com.scorpion_a.studentapp.activities.FAQSActivity;
import com.scorpion_a.studentapp.activities.LoginScreen;
import com.scorpion_a.studentapp.activities.MyRequestsActivity;
import com.scorpion_a.studentapp.activities.PrintoutActivity;
import com.scorpion_a.studentapp.activities.RegProposalActivity;
import com.scorpion_a.studentapp.activities.ResultsActivity;
import com.scorpion_a.studentapp.activities.SettingsActivity;
import com.scorpion_a.studentapp.activities.StaffProfilePageActivity;
import com.scorpion_a.studentapp.activities.StaffSettingsActivity;
import com.scorpion_a.studentapp.activities.StudentProfile;
import com.scorpion_a.studentapp.activities.TestYourselfActivity;
import com.scorpion_a.studentapp.activities.TutorialActivity;
import com.scorpion_a.studentapp.model.MoreListData;
import com.scorpion_a.studentapp.model.StaffMenuListData;
import com.scorpion_a.studentapp.utils.SharedPreferenceClass;

public class StaffMenuListAdapter extends RecyclerView.Adapter<StaffMenuListAdapter.ViewHolder> {
    private StaffMenuListData[] staffmenudata;
    private Context context;

    // RecyclerView recyclerView;
    public StaffMenuListAdapter(StaffMenuListData[] staffmenudata,Context context) {
        this.staffmenudata = staffmenudata;
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
        final StaffMenuListData staffMenuListData = staffmenudata[position];
        holder.tvMore.setText(staffmenudata[position].getmenuItemTitle());
        holder.clMoreItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0:
                        context.startActivity(new Intent(context, StaffProfilePageActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, StaffSettingsActivity.class));
                        break;
//                    case 6:
//                        context.startActivity(new Intent(context, AppTechSupportActivity.class));
//                        break;
                    case 2:
                        SharedPreferenceClass.clearSharedPrefrences(context);
                        context.startActivity(new Intent(context, LoginScreen.class));
                        ((Activity)context).finishAffinity();
                        break;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return staffmenudata.length;
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