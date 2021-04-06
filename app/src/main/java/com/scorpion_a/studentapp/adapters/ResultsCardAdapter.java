package com.scorpion_a.studentapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.model.PropCardData;
import com.scorpion_a.studentapp.model.ResultsCardData;

public class ResultsCardAdapter extends RecyclerView.Adapter<ResultsCardAdapter.ViewHolder> {
    private ResultsCardData[] resultsdata;

    // RecyclerView recyclerView;
    public ResultsCardAdapter(ResultsCardData[] resultsdata) {
        this.resultsdata = resultsdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View ResultsCardItem = layoutInflater.inflate(R.layout.results_card_titles_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(ResultsCardItem);
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
            setHeaderBg(holder.tvregNumber);
            setHeaderBg(holder.tvregCode);
            setHeaderBg(holder.tvregSubject);
            setHeaderBg(holder.tvregUnits);
            holder.tvregNumber.setText(R.string.nump);
            holder.tvregCode.setText(R.string.scodep);
            holder.tvregSubject.setText(R.string.snamep);
            holder.tvregUnits.setText(R.string.sgrade);
        }else {
            final ResultsCardData ResultsCardData = resultsdata[position-1];
            setContentBg( holder.tvregNumber);
            setContentBg( holder.tvregCode);
            setContentBg( holder.tvregSubject);
            setContentBg( holder.tvregUnits);
            holder.tvregNumber.setText(ResultsCardData.getItemNumber());
            holder.tvregCode.setText(ResultsCardData.getItemCode());
            holder.tvregSubject.setText(ResultsCardData.getItemSubject());
            holder.tvregUnits.setText(ResultsCardData.getItemGrade());
        }
    }


    @Override
    public int getItemCount() {
        return resultsdata.length+ 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvregNumber;
        public TextView tvregCode;
        public TextView tvregSubject;
        public TextView tvregUnits;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvregNumber = itemView.findViewById(R.id.tvItemNumber);
            this.tvregCode = itemView.findViewById(R.id.tvItemCode);
            this.tvregSubject = itemView.findViewById(R.id.tvItemSubject);
            this.tvregUnits = itemView.findViewById(R.id.tvItemGrade);
        }
    }
}