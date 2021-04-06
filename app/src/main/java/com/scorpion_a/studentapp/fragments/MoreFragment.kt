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
    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_more, container, false)
        toolbar=view.header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.menu)
        val moreListData: Array<MoreListData> = arrayOf<MoreListData>(
            MoreListData(
                getString(R.string.profile_page)
            ),
            MoreListData(
                getString(R.string.my_requests)
            ),
            MoreListData(
                getString(R.string.printout)
            ),
            MoreListData(
                getString(R.string.proposal)
            ),
            MoreListData(
                getString(R.string.my_results)
            ),
            MoreListData(
                getString(R.string.test_yourself)
            ),
            MoreListData(
                getString(R.string.tutorials)
            ),
            MoreListData(
                getString(R.string.faqs_and_support)
            ),
            MoreListData(
                getString(R.string.about_hti)
            ),
            MoreListData(
                getString(R.string.settings)
            ),
//            MoreListData(
//                "Application Technical Support"
//            ),
            MoreListData(
                getString(R.string.logout)
            )
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