package com.scorpion_a.studentapp.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import carbon.widget.Button
import com.scorpion_a.studentapp.adapters.MoreListAdapter
import com.scorpion_a.studentapp.model.MoreListData
import com.scorpion_a.studentapp.R
import kotlinx.android.synthetic.main.fragment_notification.view.*

class MoreFragment  : Fragment() {
    lateinit var buSwitch: Button
    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_more, container, false)
        buSwitch = view.findViewById(R.id.buSwitch)
        toolbar=view.header.findViewById(R.id.toolbar)
        toolbar.title="Menu"
        val moreListData: Array<MoreListData> = arrayOf<MoreListData>(
            MoreListData(
                "Profile Page"
            ),
            MoreListData(
                "Printout"
            ),
            MoreListData(
                "Registration Proposal"
            ),
            MoreListData(
                "Test Yourself"
            ),
            MoreListData(
                "Tutorial Page"
            ),
            MoreListData(
                "FAQs and Support"
            ),
            MoreListData(
                "Application Technical Support"
            ),
            MoreListData(
                "Log Out"
            )
        )

        recyclerView = view.findViewById(R.id.rvMore)
        val adapter = MoreListAdapter(moreListData,view.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter

        buSwitch.setOnClickListener {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        return view

    }
    companion object {
        fun newInstance(): MoreFragment = MoreFragment()
    }



}