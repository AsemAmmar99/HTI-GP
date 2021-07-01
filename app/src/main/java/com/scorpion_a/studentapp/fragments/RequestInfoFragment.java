package com.scorpion_a.studentapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.Spanned;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.scorpion_a.studentapp.activities.ConfirmRequestActivity;
import com.scorpion_a.studentapp.R;

import java.util.Objects;

public class RequestInfoFragment extends BottomSheetDialogFragment {
    String rDesc;
    String rCount;
    String rTotal;
    String rId;
    String limit;
    public static RequestInfoFragment newInstance() {
        RequestInfoFragment fragment = new RequestInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void alerting(){

        SpannableString title = new SpannableString(getString(R.string.attention));
        title.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.light_black)),
                0,
                title.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        SpannableString message = new SpannableString(getString(R.string.plz_fill));
        message.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.light_black)),
                0,
                message.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.alert)));
    }
    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_request_info, null);
//        val data =  arguments?.getString("key")

        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        String title= getArguments().getString("title");
        String price= getArguments().getString("price");
        if(Objects.equals(getArguments().getString("limit"), null)) {

            limit = "10000000";
            TextView tvMaxLimitValue=contentView.findViewById(R.id.tvRequestMaxLimitValue);
            tvMaxLimitValue.setText(R.string.nml);
        }   else {
            limit=getArguments().getString("limit");
        }
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
                }else if (Integer.parseInt(etcount.getText().toString()) > Integer.parseInt(limit)){
                    Context context = Objects.requireNonNull(RequestInfoFragment.this.getActivity()).getApplicationContext();
                    Toast.makeText(context, R.string.maxLim, Toast.LENGTH_SHORT).show();
                }else{
                    alertingCIBAcc();
                }
            }
        });
    }

    public void alertingNot(){

        SpannableString title = new SpannableString(getString(R.string.attention));
        title.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.light_black)),
                0,
                title.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        SpannableString message = new SpannableString(getString(R.string.pptmf));
        message.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.light_black)),
                0,
                message.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .create();
        alertDialog.show();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.alert)));
    }

    public void alertingCIBAcc(){

        SpannableString title = new SpannableString(getString(R.string.attention));
        title.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.light_black)),
                0,
                title.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        SpannableString message = new SpannableString(getString(R.string.cib_account));
        message.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.light_black)),
                0,
                message.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.yes),
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
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertingNot();
                                dialog.dismiss();
                            }
                        })
                .create();
        alertDialog.show();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.alert)));
    }



}

