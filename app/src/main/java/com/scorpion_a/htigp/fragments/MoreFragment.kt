package com.scorpion_a.htigp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.htigp.adapters.MoreListAdapter
import com.scorpion_a.htigp.model.MoreListData
import com.scorpion_a.htigp.R

class MoreFragment  : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_more, container, false)
        val moreListData: Array<MoreListData> = arrayOf<MoreListData>(
            MoreListData(
                "Profile Page"
            ),
            MoreListData(
                "Print Out"
            ),
            MoreListData(
                "Registration Proposal"
            ),
            MoreListData(
                "Test Your Self"
            ),
            MoreListData(
                "Tutorial Page"
            ),
            MoreListData(
                "FAQ and Support"
            ),
            MoreListData(
                "Application Technical Support"
            ),
            MoreListData(
                "Rate The App"
            ),
            MoreListData(
                "Log Out"
            ),
        )

        recyclerView = view.findViewById(R.id.rvMore)
        val adapter = MoreListAdapter(moreListData,view.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        return view
    }
    companion object {
        fun newInstance(): MoreFragment = MoreFragment()
    }
}