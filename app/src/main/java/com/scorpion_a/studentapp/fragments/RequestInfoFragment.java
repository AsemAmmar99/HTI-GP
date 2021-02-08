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
                    int servicePrice = Integer.parseInt(price);
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
                    startActivity(new Intent(getContext(), ConfirmRequestActivity.class)
                            .putExtra("desc", rDesc)
                            .putExtra("count", rCount)
                            .putExtra("total", rTotal));
                    dialog.dismiss();
                }
            }
        });
    }



}

