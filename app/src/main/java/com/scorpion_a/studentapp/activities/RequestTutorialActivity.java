package com.scorpion_a.studentapp.activities;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.scorpion_a.studentapp.R;
import com.scorpion_a.studentapp.adapters.RequestsPageerAdapter;
import com.scorpion_a.studentapp.utils.Lang;
import com.scorpion_a.studentapp.utils.SharedPreferenceClass;
import com.scorpion_a.studentapp.utils.Theme;

import java.util.ArrayList;

public class RequestTutorialActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<Integer> imageList;
    ArrayList<String> pointsList;
    ArrayList<String> pointsValueList;
    LinearLayout layout_dot;
    TextView[] dot;




    @Override
    protected void onCreate (Bundle savedInstanceState) {
        Lang.Companion.loadLocate(this);
        Theme.Companion.checkTheme(this, getDelegate());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_tutorial);
//        View header=findViewById(R.id.header);
//        Toolbar toolbar= header.findViewById(R.id.toolbar);
//        toolbar.  ="Requests Page"
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        layout_dot = (LinearLayout) findViewById(R.id.layout_dot);
        imageList = new ArrayList<>();
        pointsList = new ArrayList<>();
        pointsValueList = new ArrayList<>();

        imageList.add(R.drawable.first);
        imageList.add(R.drawable.second);
        imageList.add(R.drawable.third);

        pointsList.add("1- In this page you can request some of HTI\n" +
                "Services like your ID, your Certificates if you are a\n" +
                "graduated student and proove of your study in\n" +
                "English etc..");
        pointsList.add("2- How can I request a Request?");
        pointsList.add("3- Where I can see My Requests?");

        pointsValueList.add("- you will see a list of requests names which you can\n" +
                "request, this list will appears depend on your\n" +
                "department (CS or Engineering or Business) and you\n" +
                "statues (Student or Graduated ), select a request and\n" +
                "an alert will appear to you and ask you about many of\n" +
                "copies do you need and show the price for you and\n" +
                "the total price like that:");

        pointsValueList.add("- After you know the total Price of your Request, go to\n" +
                "CIB Bank and pay the money and take the reset, Then\n" +
                "take a clearly image of the reset, then get back to the\n" +
                "application and select from the alert you are already\n" +
                "pay the money, then you will navigate to the\n" +
                "confirmation Request Page and upload you photo and\n" +
                "check your data. Then press Confrim Button.");

        pointsValueList.add("- After pressing your Confirm Button you will navigate\n" +
                "to My request page where you will show your Request\n" +
                "Information.");

        RequestsPageerAdapter pagerAdapter = new RequestsPageerAdapter(getApplicationContext(), imageList,pointsList,pointsValueList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageMargin(20);
        addDot(0);

        // whenever the page changes
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                addDot(i);
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        if(Lang.Companion.getLang  (this).equals("ar")) {
            viewPager.setRotationY(180);
        }

    }
    public void addDot(int page_position) {
        dot = new TextView[imageList.size()];
        layout_dot.removeAllViews();

        for (int i = 0; i < dot.length; i++) {;
            dot[i] = new TextView(this);
            dot[i].setText(Html.fromHtml("&#9673;"));
            dot[i].setTextSize(35);
            dot[i].setTextColor(getResources().getColor(R.color.light_black));
            layout_dot.addView(dot[i]);
        }
        //active dot
        dot[page_position].setTextColor(getResources().getColor(R.color.blue));
    }
}
