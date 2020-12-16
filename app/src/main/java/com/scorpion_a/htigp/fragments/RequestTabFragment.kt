package com.scorpion_a.htigp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.htigp.R
import com.scorpion_a.htigp.adapters.RequestListAdapter
import com.scorpion_a.htigp.model.RequestListData

class RequestTabFragment : Fragment() {
    lateinit var  recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_request_tab, container, false)
        val requestListData: Array<RequestListData> = arrayOf<RequestListData>(
            RequestListData(
                "Arabic Graduation certificate"
            ),
            RequestListData(
                "English Graduation certificate"
            ),
            RequestListData(
                "Arab equivalent certificate"
            ),
            RequestListData(
                "Certificate of good conduct"
            ),
            RequestListData(
                "Arabic estimate statement"
            ),
            RequestListData(
                "Arabic Graduation certificate"
            ),
            RequestListData(
                "English statement of estimates"
            ),
            RequestListData(
                "Proof of enrollment"
            ),
            RequestListData(
                "English Certificate of study materials"
            ),
            RequestListData(
                "Identifying Card"
            ),
        )

        recyclerView = view.findViewById(R.id.rvRequest)
        val adapter = RequestListAdapter(requestListData, context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false )
        recyclerView.adapter = adapter
        return view
    }
    companion object {
        fun newInstance(): RequestTabFragment = RequestTabFragment()
    }
}