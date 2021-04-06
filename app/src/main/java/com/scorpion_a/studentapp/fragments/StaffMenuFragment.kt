package com.scorpion_a.studentapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.StaffMenuListAdapter
import com.scorpion_a.studentapp.model.StaffMenuListData
import kotlinx.android.synthetic.main.fragment_notification.view.*

class StaffMenuFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_staff_menu, container, false)
        toolbar=view.header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.menu)
        val staffMenuListData: Array<StaffMenuListData> = arrayOf<StaffMenuListData>(
            StaffMenuListData(
                getString(R.string.profile_page)
            ),
            StaffMenuListData(
                getString(R.string.profile_page)
            ),
//            MoreListData(
//                "Application Technical Support"
//            ),
            StaffMenuListData(
                getString(R.string.logout)
            )
        )

        recyclerView = view.findViewById(R.id.rvStaffMenu)
        val adapter = StaffMenuListAdapter(staffMenuListData,view.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        return view

    }
    companion object {
        fun newInstance(): StaffMenuFragment = StaffMenuFragment()
    }



}