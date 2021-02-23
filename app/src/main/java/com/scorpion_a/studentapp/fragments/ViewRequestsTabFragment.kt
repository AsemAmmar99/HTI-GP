package com.scorpion_a.studentapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.ViewRequestsListAdapter
import com.scorpion_a.studentapp.model.ViewRequestsListData

class ViewRequestsTabFragment  : Fragment() {
    lateinit var  recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.activity_my_requests_page, container, false)
//        val viewRequestsListData: Array<ViewRequestsListData> = arrayOf<ViewRequestsListData>(
//            ViewRequestsListData(
//                "188148",
//                "Arabic Graduation certificate",
//                "Pending",
//                "2",
//                "4:00PM 4/4/2021"
//            ),
//            ViewRequestsListData(
//                "188149",
//                "English Graduation certificate zzzzzzzzzzzzzzzzzzz",
//                "Done",
//                "2",
//                "4:00PM 4/4/2021"
//            ),
//            ViewRequestsListData(
//                "188160",
//                "Proof of Enrollment",
//                "Missing Requirements",
//                "2",
//                "4:00PM 4/4/2021"
//            )
//        )
//
//        recyclerView= view.findViewById(R.id.rvViewRequests)
//        val adapter = ViewRequestsListAdapter(
//            viewRequestsListData
//        )
//        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = LinearLayoutManager(view.context)
//        recyclerView.adapter = adapter

        return view
    }
    companion object {
        fun newInstance(): ViewRequestsTabFragment = ViewRequestsTabFragment()
    }
}