package com.scorpion_a.htigp

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_login_screen.*


class LoginScreen : AppCompatActivity() {
    var tabLayout:TabLayout ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        buLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        tabLayout = findViewById(R.id.tabs)
        tabLayout!!.addTab(tabLayout!!.newTab().setText("English"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Arabic"))
        setTabBG(R.drawable.tab_left_select, R.drawable.tab_right_unselect)
        tabLayout!!.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab:TabLayout.Tab) {
                if (tabLayout!!.getSelectedTabPosition() === 0)
                {
                    setTabBG(R.drawable.tab_left_select, R.drawable.tab_right_unselect)
                }
                else
                {
                    setTabBG(R.drawable.tab_left_unselect, R.drawable.tab_right_select)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        

        tvHTI.setText("")
        tvHTI.setCharacterDelay(80)
        tvHTI.animateText("Higher Technological Institute")

    }

    private fun setTabBG(tab1:Int, tab2:Int) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            val tabStrip = tabLayout!!.getChildAt(0) as ViewGroup
            val tabView1 = tabStrip.getChildAt(0)
            val tabView2 = tabStrip.getChildAt(1)
            if (tabView1 != null)
            {
                val paddingStart = tabView1.getPaddingStart()
                val paddingTop = tabView1.getPaddingTop()
                val paddingEnd = tabView1.getPaddingEnd()
                val paddingBottom = tabView1.getPaddingBottom()
                ViewCompat.setBackground(tabView1, AppCompatResources.getDrawable(tabView1.getContext(), tab1))
                ViewCompat.setPaddingRelative(tabView1, paddingStart, paddingTop, paddingEnd, paddingBottom)
            }
            if (tabView2 != null)
            {
                val paddingStart = tabView2.getPaddingStart()
                val paddingTop = tabView2.getPaddingTop()
                val paddingEnd = tabView2.getPaddingEnd()
                val paddingBottom = tabView2.getPaddingBottom()
                ViewCompat.setBackground(tabView2, AppCompatResources.getDrawable(tabView2.getContext(), tab2))
                ViewCompat.setPaddingRelative(tabView2, paddingStart, paddingTop, paddingEnd, paddingBottom)
            }
        }
    }

}