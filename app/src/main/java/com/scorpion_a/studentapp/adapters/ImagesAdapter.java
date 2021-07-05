package com.scorpion_a.studentapp.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.utils.Lang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import carbon.widget.ImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImagesAdapter extends PagerAdapter {

    Context context;
    ArrayList<String> image;

    public ImagesAdapter(Context context, ArrayList<String> image) {
        this.context = context;
        this.image = image;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public  Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_pager_item, container, false);
        ImageView imageView =  view.findViewById(R.id.ivImage);
//        imageView.setBackgroundResource(image.get(position));

        if(Lang.Companion.getLang  (context).equals("ar")) {
            imageView.setRotationY(180);

        }
        ImageView imageV =new ImageView(context);

//        val image = ImageView(this)
//        image.setImageResource(R.drawable.receipt);

        Picasso.with(context)
                .load("https://app.jabbarproject.com/" +image.get(position)

                )    .placeholder(R.drawable.htilogo)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog nagDialog = new Dialog(context);
                nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                nagDialog.setContentView(R.layout.image_pager_item);
                ImageView imageView1 = (ImageView)nagDialog.findViewById(R.id.ivImage);


                Glide
                        .with(context)
                        .load("https://app.jabbarproject.com/" +image.get(position))
                .into(imageView1);

                nagDialog.show();
            }
        });
//        PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (imageV.getParent() != null)
//                {
//                    ( (ViewGroup) imageV.getParent()).removeView(imageV);
//                }
//                AlertDialog.Builder dialog=new AlertDialog.Builder(context).setPositiveButton(context.getString(R.string.ok),
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                dialog.setView(imageV).create().show();
//
//            }
//        });
        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
