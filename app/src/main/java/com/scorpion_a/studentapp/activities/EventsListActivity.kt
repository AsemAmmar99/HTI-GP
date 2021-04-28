package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.EventsListAdapter
import com.scorpion_a.studentapp.adapters.NewsListAdapter
import com.scorpion_a.studentapp.adapters.RequestListAdapter
import com.scorpion_a.studentapp.model.ArticlesListData
import com.scorpion_a.studentapp.model.EventsListData
import com.scorpion_a.studentapp.model.NewsListData
import com.scorpion_a.studentapp.model.responses.ArticlesResponse
import com.scorpion_a.studentapp.model.responses.RequestsResponse
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_events_list.*
import kotlinx.android.synthetic.main.activity_events_list.header
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.fragment_requests_page.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventsListActivity : AppCompatActivity() {
//    lateinit var  recyclerView: RecyclerView
    lateinit var toolbar: Toolbar
    lateinit var recyclerViewEvents: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)
        toolbar=header.findViewById(R.id.toolbar)
            toolbar.title=getString(R.string.events_page)

//            val eventsListData: Array<NewsListData> = arrayOf<NewsListData>(
//            NewsListData(
//                "The Institute Guide to Coordination",
//                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
//                "1:00AM 1/1/2020"
//            ),
//            NewsListData(
//                "The Institute Guide to Coordination",
//                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
//                ,"1:00AM 1/1/2020"
//            ),
//            NewsListData(
//                "The Institute Guide to Coordination",
//                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
//                ,"1:00AM 1/1/2020"
//            ),
//            NewsListData(
//                "The Institute Guide to Coordination",
//                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
//                ,"1:00AM 1/1/2020"
//            ),
//
//            NewsListData(
//                "The Institute Guide to Coordination",
//                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
//                ,"1:00AM 1/1/2020"
//            )
//        )


//        val adapterEvents = NewsListAdapter(eventsListData, this)
//        rvEventList.setHasFixedSize(true)
//        rvEventList.layoutManager = LinearLayoutManager(this)
//        rvEventList.adapter = adapterEvents

        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${SharedPreferenceClass.loadString(this,"TOKEN")}").build()
                chain.proceed(request)
            }.build())
            .build()

        val client = retrofit.create(Service::class.java)
        val call = client.getArticlesData()
        progressBarEventsList.visibility = View.VISIBLE
        clEventsList.visibility = View.INVISIBLE
        call.enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>?,
                response: Response<ArticlesResponse>?
            ) {
                if (response!!.isSuccessful())
                {
                    var eventsListData: ArrayList<ArticlesListData>?=ArrayList()

                    response.body().data.map {
                        if (it.type.equals("event")) {
                            eventsListData?.add(ArticlesListData(it.id,
                                it.title,
                                it.images,
                                it.type,
                                it.date))
//                            eventsListData=   arrayOf<ArticlesListData>(
//                                ArticlesListData(it.id,it.title, it.date, it.images,it.type))
                            recyclerViewEvents = findViewById(R.id.rvEventList)
                            val adapterEvents =
                                NewsListAdapter(eventsListData, this@EventsListActivity)
                            recyclerViewEvents.setHasFixedSize(true)
                            recyclerViewEvents.layoutManager =
                                LinearLayoutManager(this@EventsListActivity,
                                    LinearLayoutManager.VERTICAL, false)
                        progressBarEventsList.visibility = View.GONE
                        clEventsList.visibility = View.VISIBLE
                            recyclerViewEvents.adapter = adapterEvents
                        }
                    }
                }else {
                    progressBarEventsList.visibility = View.GONE
                    Toast.makeText(
                        this@EventsListActivity,
                        getString(R.string.went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ArticlesResponse>?, t: Throwable?) {
                Log.i("test", t.toString())
            }


        })

    }
}