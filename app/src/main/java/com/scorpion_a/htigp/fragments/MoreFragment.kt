package com.scorpion_a.htigp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.htigp.adapters.MoreListAdapter
import com.scorpion_a.htigp.model.MoreListData
import com.scorpion_a.htigp.R
import kotlinx.android.synthetic.main.fragment_notification.view.*

class MoreFragment  : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_more, container, false)
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