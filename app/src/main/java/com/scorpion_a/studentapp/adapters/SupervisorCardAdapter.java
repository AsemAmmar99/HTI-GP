package com.scorpion_a.studentapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.model.SupervisorCardData;

public class SupervisorCardAdapter extends RecyclerView.Adapter<SupervisorCardAdapter.ViewHolder> {
    private SupervisorCardData[] supervisordata;

    // RecyclerView recyclerView;
    public SupervisorCardAdapter(SupervisorCardData[] supervisordata) {
        this.supervisordata = supervisordata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View supervisorCardItem = layoutInflater.inflate(R.layout.supervisor_card_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(supervisorCardItem);
        return viewHolder;
    }

    private void setHeaderBg(View view){
        view.setBackgroundResource(R.drawable.table_header_cell_bg);
    }

    private void setContentBg(View view){
        view.setBackgroundResource(R.drawable.table_content_cell_bg);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position ==0){
            setHeaderBg(holder.tvNumber);
            setHeaderBg(holder.tvStatus);
            setHeaderBg(holder.tvDepartment);
            setHeaderBg(holder.tvDate);
            setHeaderBg(holder.tvName);
            holder.tvNumber.setText(R.string.rnumber);
            holder.tvStatus.setText(R.string.rstatus);
            holder.tvDepartment.setText(R.string.rdep);
            holder.tvDate.setText(R.string.rdate);
            holder.tvName.setText(R.string.remp);
        }else {
            final SupervisorCardData supervisorCardData = supervisordata[position - 1];
            setContentBg(holder.tvNumber);
            setContentBg(holder.tvStatus);
            setContentBg(holder.tvDepartment);
            setContentBg(holder.tvDate);
            setContentBg(holder.tvName);
            holder.tvNumber.setText(supervisorCardData.getItemNumber());
            holder.tvStatus.setText(supervisorCardData.getItemStatus());
            holder.tvDepartment.setText(supervisorCardData.getItemDepartment());
            holder.tvDate.setText(supervisorCardData.getItemDate());
            holder.tvName.setText(supervisorCardData.getItemName());
        }
    }


    @Override
    public int getItemCount() {
        return supervisordata.length+ 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNumber;
        public TextView tvStatus;
        public TextView tvDepartment;
        public TextView tvDate;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvNumber = itemView.findViewById(R.id.tvSupervisorNumber);
            this.tvStatus = itemView.findViewById(R.id.tvItemStatus);
            this.tvDepartment = itemView.findViewById(R.id.tvItemDepartment);
            this.tvDate = itemView.findViewById(R.id.tvItemDate);
            this.tvName = itemView.findViewById(R.id.tvItemName);
        }
    }
}