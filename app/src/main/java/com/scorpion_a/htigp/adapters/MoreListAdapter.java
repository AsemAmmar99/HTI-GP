package com.scorpion_a.htigp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.htigp.model.MoreListData;
import com.scorpion_a.htigp.R;

public class MoreListAdapter extends RecyclerView.Adapter<MoreListAdapter.ViewHolder> {
    private MoreListData[] moredata;

    // RecyclerView recyclerView;
    public MoreListAdapter(MoreListData[] moredata) {
        this.moredata = moredata;
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
    }


    @Override
    public int getItemCount() {
        return moredata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMore;


        public ViewHolder(View itemView) {
            super(itemView);
            this.tvMore = itemView.findViewById(R.id.tvMore);
        }
    }
}
