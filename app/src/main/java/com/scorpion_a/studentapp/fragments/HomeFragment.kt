package com.scorpion_a.studentapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import carbon.widget.Button
import com.google.gson.GsonBuilder
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.activities.EventsListActivity
import com.scorpion_a.studentapp.adapters.EventsListAdapter
import com.scorpion_a.studentapp.adapters.NewsListAdapter
import com.scorpion_a.studentapp.model.EventsListData
import com.scorpion_a.studentapp.model.NewsListData
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${SharedPreferenceClass.loadString(context,"TOKEN")}").build()
                chain.proceed(request)
            }.build())
            .build()

        val client = retrofit.create(Service::class.java)


        val call = client.getUserData()
        call.enqueue(object : Callback<UserDataResponce> {
            override fun onResponse(
                call: Call<UserDataResponce>,
                response: Response<UserDataResponce>
            ) {

                if (response.isSuccessful()){
                    tvDepartment.text="Department: " + response.body().data?.department
                }else{
                    Toast.makeText(context, "Something went wrong, please check your data", Toast.LENGTH_SHORT).show()
                }
                // Catching Responses From Retrofit

                Log.d("TAG", "onResponseisSuccessful: " + response.isSuccessful());
                Log.d("TAG", "onResponsebody: " + response.body());
                Log.d("TAG", "onResponseerrorBody: " + response.errorBody());
                Log.d("TAG", "onResponsemessage: " + response.message());
                Log.d("TAG", "onResponsecode: " + response.code());
                Log.d("TAG", "onResponseheaders: " + response.headers());
                Log.d("TAG", "onResponseraw: " + response.raw());
                Log.d("TAG", "onResponsetoString: " + response.toString());

            }



            override fun onFailure(call: Call<UserDataResponce>?, t: Throwable?) {
                Log.i("test", t.toString())
            }
        })
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