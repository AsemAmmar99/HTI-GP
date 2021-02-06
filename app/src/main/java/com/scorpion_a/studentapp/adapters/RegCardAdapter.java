package com.scorpion_a.studentapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.model.RegCardData;

public class RegCardAdapter extends RecyclerView.Adapter<RegCardAdapter.ViewHolder> {
    private RegCardData[] regdata;

    // RecyclerView recyclerView;
    public RegCardAdapter(RegCardData[] regdata) {
        this.regdata = regdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View regCardItem = layoutInflater.inflate(R.layout.reg_card_titles_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(regCardItem);
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
            setHeaderBg(holder.tvregGroup);
            setHeaderBg(holder.tvregUnits);
            holder.tvregNumber.setText("N");
            holder.tvregCode.setText("Code");
            holder.tvregSubject.setText("Subject");
            holder.tvregGroup.setText("Group");
            holder.tvregUnits.setText("Units");
        }else {
            final RegCardData regCardData = regdata[position-1];
            setContentBg( holder.tvregNumber);
            setContentBg( holder.tvregCode);
            setContentBg( holder.tvregSubject);
            setContentBg( holder.tvregGroup);
            setContentBg( holder.tvregUnits);
            holder.tvregNumber.setText(regCardData.getItemNumber());
            holder.tvregCode.setText(regCardData.getItemCode());
            holder.tvregSubject.setText(regCardData.getItemSubject());
            holder.tvregGroup.setText(regCardData.getItemGroup());
            holder.tvregUnits.setText(regCardData.getItemUnits());
        }
    }


    @Override
    public int getItemCount() {
        return regdata.length+ 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvregNumber;
        public TextView tvregCode;
        public TextView tvregSubject;
        public TextView tvregGroup;
        public TextView tvregUnits;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvregNumber = itemView.findViewById(R.id.tvItemNumber);
            this.tvregCode = itemView.findViewById(R.id.tvItemCode);
            this.tvregSubject = itemView.findViewById(R.id.tvItemSubject);
            this.tvregGroup = itemView.findViewById(R.id.tvItemGroup);
            this.tvregUnits = itemView.findViewById(R.id.tvItemUnits);
        }
    }
}