package com.scorpion_a.htigp.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import carbon.widget.Button
import com.scorpion_a.htigp.adapters.NewsListAdapter
import com.scorpion_a.htigp.model.NewsListData
import com.scorpion_a.htigp.R
import com.scorpion_a.htigp.activities.LoginScreen

class HomeFragment : Fragment() {
    lateinit var buLogout:Button
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val view=   inflater.inflate(R.layout.fragment_home, container, false)
        buLogout = view.findViewById(R.id.buLogout)
        buLogout.setOnClickListener {
            val intent = Intent(view.context, LoginScreen::class.java)
            startActivity(intent)
            finishAffinity(view.context as Activity)
        }
        val newsListData: Array<NewsListData> = arrayOf<NewsListData>(
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            )
        )

        recyclerView = view.findViewById(R.id.rvNews)
        val adapter = NewsListAdapter(newsListData, context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter

        return view
    }
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

}