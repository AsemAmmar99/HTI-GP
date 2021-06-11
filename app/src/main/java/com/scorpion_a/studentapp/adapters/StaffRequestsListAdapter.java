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
import com.scorpion_a.studentapp.activities.RejectedRequestsDetailsActivity;
import com.scorpion_a.studentapp.activities.AcceptedRequestsDetailsActivity;
import com.scorpion_a.studentapp.activities.PendingRequestsDetailsActivity;
import com.scorpion_a.studentapp.model.StaffRequestsListData;
import com.scorpion_a.studentapp.model.ViewRequestsListData;

import java.util.ArrayList;

public class StaffRequestsListAdapter extends RecyclerView.Adapter<StaffRequestsListAdapter.ViewHolder> {
    private ArrayList<ViewRequestsListData> staffreqdata;
    private Context context;
    private ArrayList<ViewRequestsListData> itemList;
    private String pageName;

    // RecyclerView recyclerView;
    public StaffRequestsListAdapter(ArrayList<ViewRequestsListData> staffteqdata, Context context, String pageName) {
        this.staffreqdata = staffteqdata;
        this.context = context;
        this.pageName = pageName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View staffReqItem = layoutInflater.inflate(R.layout.staff_request_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(staffReqItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ViewRequestsListData myListData = staffreqdata.get(position);
        holder.tvReqNum.setText(staffreqdata.get(position).getId());
        holder.clStaffItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageName.equals("pending")) {
                    context.startActivity(new Intent(context, PendingRequestsDetailsActivity.class));
                }else if (pageName.equals("accepted")){
                    context.startActivity(new Intent(context, AcceptedRequestsDetailsActivity.class));
                }else if (pageName.equals("rejected")){
                    context.startActivity(new Intent(context, RejectedRequestsDetailsActivity.class));
                }else{
                    context.startActivity(new Intent(context, PendingRequestsDetailsActivity.class));
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return staffreqdata.size();
    }

    public void filterList(ArrayList<ViewRequestsListData> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        staffreqdata = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        staffreqdata = new StaffRequestsListData[staffreqdata.length];
//        if (charText.length() == 0) {
//            staffreqdata ;
//        } else {
//            for (AnimalNames wp : arraylist) {
//                if (wp.getAnimalName().toLowerCase(Locale.getDefault()).contains(charText)) {
//                    animalNamesList.add(wp);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }

//    public void filterList(ArrayList<StaffRequestsListData> filteredNames) {
//        this.itemList = filteredNames;
//        notifyDataSetChanged();
//    }

//    private Filter fRecords;
//
//    @Override
//    public Filter getFilter() {
//        if(fRecords == null) {
//            fRecords=new RecordFilter();
//        }
//        return fRecords;
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvReqNum;
        public ConstraintLayout clStaffItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvReqNum = itemView.findViewById(R.id.tvRequestNum);
            this.clStaffItem = itemView.findViewById(R.id.clStaffRequestItem);
        }
    }

//    private class RecordFilter extends Filter {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            FilterResults results = new FilterResults();
//
//            ArrayList<String> staffRecords = new ArrayList<String>();
//
//            //Implement filter logic
//            // if edittext is null return the actual list
//            if (constraint == null || constraint.length() == 0) {
//                //No need for filter
//                results.values = staffRecords;
//                results.count = staffRecords.size();
//
//            } else {
//                //Need Filter
//                // it matches the text  entered in the edittext and set the data in adapter list
//                ArrayList<String> fRecords = new ArrayList<String>();
//
//                for (String s : staffRecords) {
//                    if (s.toUpperCase().trim().contains(constraint.toString().toUpperCase().trim())) {
//                        fRecords.add(s);
//                    }
//                }
//                results.values = fRecords;
//                results.count = fRecords.size();
//            }
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            //it set the data from filter to adapter list and refresh the recyclerview adapter
//            staffreqdata = (StaffRequestsListData[]) results.values;
//            notifyDataSetChanged();
//        }
//    }
}

