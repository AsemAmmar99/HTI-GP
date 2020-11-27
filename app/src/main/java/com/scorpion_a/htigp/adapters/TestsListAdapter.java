package com.scorpion_a.htigp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.htigp.R;
import com.scorpion_a.htigp.model.TestsListData;

public class TestsListAdapter extends RecyclerView.Adapter<TestsListAdapter.ViewHolder> {
    private TestsListData[] testsdata;

    // RecyclerView recyclerView;
    public TestsListAdapter(TestsListData[] testsdata) {
        this.testsdata = testsdata;
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
    }


    @Override
    public int getItemCount() {
        return testsdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTest;


        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTest = itemView.findViewById(R.id.tvTest);
        }
    }
}
