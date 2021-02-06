package com.scorpion_a.studentapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.RequestListAdapter
import com.scorpion_a.studentapp.model.RequestListData
import kotlinx.android.synthetic.main.fragment_notification.view.*

class RequestsPageFragment : Fragment() {
    lateinit var toolbar: Toolbar
    var tabLayout: TabLayout?= null
    lateinit var  recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_requests_page, container, false)
        toolbar=view.header.findViewById(R.id.toolbar)
        toolbar.title="Requests Page"
        val requestListData: Array<RequestListData> = arrayOf<RequestListData>(
            RequestListData(
                "Arabic Graduation certificate","30"
            ),
            RequestListData(
                "English Graduation certificate","50"
            ),
            RequestListData(
                "Arab equivalent certificate","70"
            ),
            RequestListData(
                "Certificate of good conduct","100"
            ),
            RequestListData(
                "Arabic estimate statement","30"
            ),
            RequestListData(
                "Arabic Graduation certificate","20"
            ),
            RequestListData(
                "English statement of estimates","30"
            ),
            RequestListData(
                "Proof of enrollment","50"
            ),
            RequestListData(
                "English Certificate of study materials","40"
            ),
            RequestListData(
                "Identifying Card","30"
            ),
            RequestListData(
                "Recommendation Letter","40"
            ),
        )

        recyclerView = view.findViewById(R.id.rvRequest)
        val adapter = RequestListAdapter(requestListData, context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context,
            LinearLayoutManager.VERTICAL,false )
        recyclerView.adapter = adapter
        return view
    }
    companion object {
        fun newInstance(): RequestsPageFragment = RequestsPageFragment()
    }
}