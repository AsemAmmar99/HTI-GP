package com.scorpion_a.studentapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.model.ResultsCardData;
import com.scorpion_a.studentapp.model.TableListData;

import java.util.ArrayList;

public class TableDegreeAdapter extends RecyclerView.Adapter<TableDegreeAdapter.ViewHolder> {
private ArrayList<TableListData> tableListData = new  ArrayList<TableListData>();
    public TableDegreeAdapter(ArrayList<TableListData> tableListData ) {
        this.tableListData = tableListData;
    }

    @Override
    public TableDegreeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View ResultsCardItem = layoutInflater.inflate(R.layout.table_items, parent, false);
        TableDegreeAdapter.ViewHolder viewHolder = new TableDegreeAdapter.ViewHolder(ResultsCardItem);
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
    public void onBindViewHolder(TableDegreeAdapter.ViewHolder holder, int position) {

        if(position ==0){
            setHeaderBg(holder.tvItemGpa);
            setHeaderBg(holder.tvItemUnits);

            holder.tvItemGpa.setText(R.string.gpa);
            holder.tvItemUnits.setText(R.string.allowed_units);

        }else {
            final TableListData tableListDataList = tableListData.get(position - 1);
            setContentBg( holder.tvItemGpa);
            setContentBg( holder.tvItemUnits);

            holder.tvItemGpa.setText(tableListDataList.getGpa());
            holder.tvItemUnits.setText(tableListDataList.getUnits());

        }
    }


    @Override
    public int getItemCount() {
        return tableListData.size()+ 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItemGpa;
        public TextView tvItemUnits;


        public ViewHolder(View itemView) {
            super(itemView);
            this.tvItemGpa = itemView.findViewById(R.id.tvItemGpa);
            this.tvItemUnits = itemView.findViewById(R.id.tvItemUnits);

        }
    }
}
