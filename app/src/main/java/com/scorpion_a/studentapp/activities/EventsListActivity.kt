package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.EventsListAdapter
import com.scorpion_a.studentapp.adapters.NewsListAdapter
import com.scorpion_a.studentapp.model.EventsListData
import com.scorpion_a.studentapp.model.NewsListData
import kotlinx.android.synthetic.main.activity_events_list.*
import kotlinx.android.synthetic.main.activity_events_list.header
import kotlinx.android.synthetic.main.activity_profile_page.*

class EventsListActivity : AppCompatActivity() {
//    lateinit var  recyclerView: RecyclerView
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)
        toolbar=header.findViewById(R.id.toolbar)
        var more= intent.getStringExtra("More")
        if (more.equals("Events")){
            toolbar.title="Events Page"}
        if (more.equals("News")){
            toolbar.title="News Page"
        }

            val eventsListData: Array<NewsListData> = arrayOf<NewsListData>(
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "1:00AM 1/1/2020"
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


        val adapterEvents = NewsListAdapter(eventsListData, this)
        rvEventList.setHasFixedSize(true)
        rvEventList.layoutManager = LinearLayoutManager(this)
        rvEventList.adapter = adapterEvents

    }
}