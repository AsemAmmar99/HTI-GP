package com.scorpion_a.studentapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.scorpion_a.studentapp.activities.ConfirmRequestActivity;
import com.scorpion_a.studentapp.R;

public class RequestInfoFragment extends BottomSheetDialogFragment {
    String rDesc;
    String rCount;
    String rTotal;
    String rId;
    public static RequestInfoFragment newInstance() {
        RequestInfoFragment fragment = new RequestInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void alerting(){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(getString(R.string.attention));
        alertDialog.setMessage(getString(R.string.plz_fill));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_request_info, null);
//        val data =  arguments?.getString("key")

        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        String title= getArguments().getString("title");
        String price= getArguments().getString("price");
        rId= getArguments().getString("id");
        TextView tvTitle=contentView.findViewById(R.id.tvRequestNameValue);
        TextView tvPrice=contentView.findViewById(R.id.tvRequestPriceValue);
        Button send=contentView.findViewById(R.id.buSendRequest);
        TextView tvTotalcount=contentView.findViewById(R.id.tvRequestTotalPriceValue);
        EditText etcount=contentView.findViewById(R.id.etRequestCount);
        TextView tvtotal=contentView.findViewById(R.id.tvTotalPriceButton);
        tvTitle.setText(title);
        tvPrice.setText(price);
        tvTotalcount.setText(price);
        rDesc=title;
        rCount=etcount.getText().toString();
        rTotal=price;
        tvtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etcount.getText().toString().equals("")||etcount.getText().toString().equals(null)) {}else{
                    int count = Integer.parseInt(etcount.getText().toString());
                    float servicePrices = Float.parseFloat(price);
                    int servicePrice = (int) servicePrices;

                    String result = String.valueOf(count * servicePrice);
                    tvTotalcount.setText(result);
                    rCount=etcount.getText().toString();
                    rTotal=result;
                }

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etcount.getText().toString().equals("")||etcount.getText().toString().equals(null)) {
                    alerting();
                }else {
                    alertingNot();
                }
            }
        });
    }
    public void alertingNot(){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.attention))
                .setMessage("CIB Account: 100011938528. Did you pay and have the Receipt?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getContext(), ConfirmRequestActivity.class)
                                        .putExtra("desc", rDesc)
                                        .putExtra("count", rCount)
                                        .putExtra("total", rTotal)
                                        .putExtra("id", rId));
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .create();
        alertDialog.show();
    }



}

