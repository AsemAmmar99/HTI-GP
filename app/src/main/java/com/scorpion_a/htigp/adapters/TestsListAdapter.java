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
import com.scorpion_a.htigp.activities.TestQuestionsActivity;
import com.scorpion_a.htigp.model.TestsListData;

public class TestsListAdapter extends RecyclerView.Adapter<TestsListAdapter.ViewHolder> {
    private TestsListData[] testsdata;
    private Context context;

    // RecyclerView recyclerView;
    public TestsListAdapter(TestsListData[] testsdata, Context context) {
        this.testsdata = testsdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View testsItem = layoutInflater.inflate(R.layout.test_yourself_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(testsItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TestsListData testsListData = testsdata[position];
        holder.tvTest.setText(testsdata[position].getTestTitle());
        holder.clTestYourself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, TestQuestionsActivity.class);
                intent.putExtra("pagetitle",testsdata[position].getTestTitle());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return testsdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTest;
        public ConstraintLayout clTestYourself;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTest = itemView.findViewById(R.id.tvTest);
            this.clTestYourself = itemView.findViewById(R.id.clTestYourself);
        }
    }
}
