package com.scorpion_a.studentapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.scorpion_a.studentapp.R;

public class RequestInfoFragment extends BottomSheetDialogFragment {
    public static RequestInfoFragment newInstance() {
        RequestInfoFragment fragment = new RequestInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_request_info, null);
//        val data =  arguments?.getString("key")

        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        String title= getArguments().getString("title");
        String price= getArguments().getString("price");
        TextView tvTitle=contentView.findViewById(R.id.tvRequestNameValue);
        TextView tvPrice=contentView.findViewById(R.id.tvRequestPriceValue);
        Button send=contentView.findViewById(R.id.buSendRequest);
        TextView tvTotalcount=contentView.findViewById(R.id.tvRequestTotalPriceValue);
        EditText etcount=contentView.findViewById(R.id.etRequestCount);
        TextView tvtotal=contentView.findViewById(R.id.tvTotalPriceButton);
        tvTitle.setText(title);
        tvPrice.setText(price);
        tvTotalcount.setText(price);
        tvtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etcount.getText().toString().equals("")||etcount.getText().toString().equals(null)) {}else{
                    int count = Integer.parseInt(etcount.getText().toString());
                    int servicePrice = Integer.parseInt(price);
                    String result = String.valueOf(count * servicePrice);
                    tvTotalcount.setText(result);
                }

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

}

