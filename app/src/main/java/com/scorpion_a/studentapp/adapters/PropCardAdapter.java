package com.scorpion_a.studentapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.model.PropCardData;

public class PropCardAdapter extends RecyclerView.Adapter<PropCardAdapter.ViewHolder> {
    private PropCardData[] propdata;

    // RecyclerView recyclerView;
    public PropCardAdapter(PropCardData[] propdata) {
        this.propdata = propdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View propCardItem = layoutInflater.inflate(R.layout.prop_card_titles_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(propCardItem);
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
            holder.tvregNumber.setText("N");
            holder.tvregCode.setText("Code");
            holder.tvregSubject.setText("Subject");
            holder.tvregUnits.setText("Units");
        }else {
            final PropCardData propCardData = propdata[position-1];
            setContentBg( holder.tvregNumber);
            setContentBg( holder.tvregCode);
            setContentBg( holder.tvregSubject);
            setContentBg( holder.tvregUnits);
            holder.tvregNumber.setText(propCardData.getItemNumber());
            holder.tvregCode.setText(propCardData.getItemCode());
            holder.tvregSubject.setText(propCardData.getItemSubject());
            holder.tvregUnits.setText(propCardData.getItemUnits());
        }
    }


    @Override
    public int getItemCount() {
        return propdata.length+ 1;
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
            this.tvregUnits = itemView.findViewById(R.id.tvItemUnits);
        }
    }
}