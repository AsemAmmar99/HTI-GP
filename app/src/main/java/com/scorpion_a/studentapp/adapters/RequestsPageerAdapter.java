package com.scorpion_a.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.utils.Lang;

import java.util.ArrayList;

import carbon.widget.ImageView;

public class RequestsPageerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Integer> image;
    ArrayList<String> point;
    ArrayList<String> pointDetails;

    public RequestsPageerAdapter(Context context,  ArrayList<Integer> image, ArrayList<String> point, ArrayList<String> pointDetails) {
        this.context = context;
        this.image = image;
        this.point = point;
        this.pointDetails = pointDetails;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public  Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, container, false);
        ImageView imageView =  view.findViewById(R.id.ivExample);
        TextView textPoint =  view.findViewById(R.id.tvPoint);
        TextView textPointValue =  view.findViewById(R.id.tvPointValue);
        imageView.setBackgroundResource(image.get(position));
        textPoint.setText(point.get(position));
        textPointValue.setText(pointDetails.get(position));
        if(Lang.Companion.getLang  (context).equals("ar")) {
            imageView.setRotationY(180);
            textPoint.setRotationY(180);
            textPointValue.setRotationY(180);
        }
        container.addView(view);

        return view;
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