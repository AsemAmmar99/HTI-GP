package com.scorpion_a.studentapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.adapters.TableDegreeAdapter;
import com.scorpion_a.studentapp.model.TableListData;

import java.util.ArrayList;

public class TableFragment extends BottomSheetDialogFragment {

    public static TableFragment newInstance() {
        TableFragment fragment = new TableFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_table, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        RecyclerView recyclerView;
        ArrayList<TableListData> tableListData = new  ArrayList<TableListData>();
        tableListData.add(new TableListData("From 2.0 to 4.0","20"));
        tableListData.add(new TableListData("From 1.5 to 2.0","18"));
        tableListData.add(new TableListData("From 1.0 to 1.5","16"));
        tableListData.add(new TableListData("From 0.5 to 1.0","14"));
        tableListData.add(new TableListData("Lower than 0.5","12"));

        recyclerView =contentView. findViewById(R.id.rvGpaItems);
        TableDegreeAdapter adapter = new TableDegreeAdapter(tableListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);

    }

}

