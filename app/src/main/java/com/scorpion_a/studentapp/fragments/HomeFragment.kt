package com.scorpion_a.studentapp.fragments

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
import com.scorpion_a.studentapp.adapters.NewsListAdapter
import com.scorpion_a.studentapp.model.NewsListData
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.activities.EventsListActivity
import com.scorpion_a.studentapp.activities.LoginScreen
import com.scorpion_a.studentapp.adapters.EventsListAdapter
import com.scorpion_a.studentapp.model.EventsListData
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    lateinit var buLogout:Button
    lateinit var recyclerViewNews: RecyclerView
    lateinit var recyclerViewEvents: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val view=   inflater.inflate(R.layout.fragment_home, container, false)
//        buLogout = view.findViewById(R.id.buLogout)
//        buLogout.setOnClickListener {
//            val intent = Intent(view.context, LoginScreen::class.java)
//            startActivity(intent)
//            finishAffinity(view.context as Activity)
//        }
        val newsListData: Array<NewsListData> = arrayOf<NewsListData>(
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"

            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"

            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"


            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"

            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"

            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"

            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"

            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"

            )
        )
        val eventsListData: Array<EventsListData> = arrayOf<EventsListData>(
            EventsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "1:00AM 1/1/2020"
            ),
            EventsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ,"1:00AM 1/1/2020"
            ),
            EventsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"
            ),
            EventsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"
            ),

            EventsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
                ,"1:00AM 1/1/2020"
            )
        )

        recyclerViewNews = view.findViewById(R.id.rvNewsList)
        val adapterNews = NewsListAdapter(newsListData, context)
        recyclerViewNews.setHasFixedSize(true)
        recyclerViewNews.layoutManager = LinearLayoutManager(view.context)
        recyclerViewNews.adapter = adapterNews

        recyclerViewEvents = view.findViewById(R.id.rvEvents)
        val adapterEvents = EventsListAdapter(eventsListData, context)
        recyclerViewEvents.setHasFixedSize(true)
        recyclerViewEvents.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewEvents.adapter = adapterEvents

        view.tvEventsMore.setOnClickListener {
            val intent = Intent(view.context, EventsListActivity::class.java)
            intent.putExtra("More","Events")
            startActivity(intent)
        }
        view.tvNewsMoreBt.setOnClickListener {
            val intent = Intent(view.context, EventsListActivity::class.java)
            intent.putExtra("More","News")
            startActivity(intent)
        }
        return view
    }
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

}