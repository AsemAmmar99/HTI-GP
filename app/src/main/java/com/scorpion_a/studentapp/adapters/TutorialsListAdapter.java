package com.scorpion_a.studentapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.activities.TutorialDetailsActivity;
import com.scorpion_a.studentapp.model.TutorialsListData;

public class TutorialsListAdapter extends RecyclerView.Adapter<TutorialsListAdapter.ViewHolder> {
    private TutorialsListData[] tutorialsdata;
    private Context context;

    // RecyclerView recyclerView;
    public TutorialsListAdapter(TutorialsListData[] tutorialsdata, Context context) {
        this.tutorialsdata = tutorialsdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View tutorialsItem = layoutInflater.inflate(R.layout.tutorials_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(tutorialsItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TutorialsListData tutorialsListData = tutorialsdata[position];
        holder.tvTutorials.setText(tutorialsdata[position].getTutorialTitle());
        holder.clTutorialsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TutorialDetailsActivity.class);
                intent.putExtra("pagetitle",tutorialsdata[position].getTutorialTitle());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return tutorialsdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTutorials;
        public ConstraintLayout clTutorialsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTutorials = itemView.findViewById(R.id.tvTutorial);
            this.clTutorialsItem = itemView.findViewById(R.id.clTutorialsItem);
        }
    }
}