package com.scorpion_a.htigp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.scorpion_a.htigp.R
import kotlinx.android.synthetic.main.fragment_notification.view.*

class RequestsFragment : Fragment() {

    lateinit var toolbar: Toolbar
    var tabLayout: TabLayout?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_requests, container, false)
        toolbar=view.header.findViewById(R.id.toolbar)
        toolbar.title="Requests"
        tabLayout = view.findViewById(R.id.tabs)
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Request"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("View Requests"))
        setTabBG(
            R.drawable.tab_left_select,
            R.drawable.tab_right_unselect
        )
        val requestTabFragment = RequestTabFragment.newInstance()
        openFragment(requestTabFragment)
        tabLayout!!.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab:TabLayout.Tab) {
                if (tabLayout!!.getSelectedTabPosition() === 0)
                {
                    val requestTabFragment = RequestTabFragment.newInstance()
                    openFragment(requestTabFragment)
                    setTabBG(
                        R.drawable.tab_left_select,
                        R.drawable.tab_right_unselect
                    )
                }
                else
                {
                    val viewRequestsTabFragment = ViewRequestsTabFragment.newInstance()
                    openFragment(viewRequestsTabFragment)
                    setTabBG(
                        R.drawable.tab_left_unselect,
                        R.drawable.tab_right_select
                    )
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


        return view
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
    private fun openFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.realtabcontent, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    companion object {
        fun newInstance(): RequestsFragment = RequestsFragment()
    }
}