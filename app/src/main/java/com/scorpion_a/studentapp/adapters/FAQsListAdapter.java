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
import com.scorpion_a.studentapp.activities.FAQsAnswersActivity;
import com.scorpion_a.studentapp.model.FAQsListData;

public class FAQsListAdapter extends RecyclerView.Adapter<FAQsListAdapter.ViewHolder> {
    private FAQsListData[] faqsdata;
    private Context context;

    // RecyclerView recyclerView;
    public FAQsListAdapter(FAQsListData[] faqsdata, Context context) {
        this.faqsdata = faqsdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View faqsItem = layoutInflater.inflate(R.layout.faqs_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(faqsItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FAQsListData faqsListData = faqsdata[position];
        holder.tvFAQs.setText(faqsdata[position].getFaqTitle());
        holder.clFAQsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FAQsAnswersActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return faqsdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFAQs;
        public ConstraintLayout clFAQsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvFAQs = itemView.findViewById(R.id.tvFAQs);
            this.clFAQsItem = itemView.findViewById(R.id.clFAQsItem);
        }
    }
}
