package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.NewsListAdapter
import com.scorpion_a.studentapp.model.ArticlesListData
import com.scorpion_a.studentapp.model.responses.ArticlesResponse
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.fragment_notification.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsListActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var recyclerViewNews: RecyclerView
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        toolbar= header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.news_page)

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

        mSwipeRefreshLayout= findViewById(R.id.swipe_refresh_layout)
        mSwipeRefreshLayout!!.setOnRefreshListener {
            call.clone().enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>?,
                    response: Response<ArticlesResponse>?
                ) {
                    if (response!!.isSuccessful())
                    {
                        mSwipeRefreshLayout!!.isRefreshing = false
                        var newsListData: ArrayList<ArticlesListData>?=ArrayList()

                        response.body().data.map {
                            if (it.type.equals("news")) {
                                newsListData?.add(ArticlesListData(it.id,
                                    it.title,
                                    it.images,
                                    it.type,
                                    it.date))
//                            eventsListData=   arrayOf<ArticlesListData>(
//                                ArticlesListData(it.id,it.title, it.date, it.images,it.type))
                                recyclerViewNews = findViewById(R.id.rvNewsList)
                                val adapterEvents =
                                    NewsListAdapter(newsListData, this@NewsListActivity)
                                recyclerViewNews.setHasFixedSize(true)
                                recyclerViewNews.layoutManager =
                                    LinearLayoutManager(this@NewsListActivity,
                                        LinearLayoutManager.VERTICAL, false)
                                progressBarNewsList.visibility = View.GONE
                                clNewsList.visibility = View.VISIBLE
                                recyclerViewNews.adapter = adapterEvents
                            }
                        }
                    }else {
                        mSwipeRefreshLayout!!.isRefreshing = false
                        progressBarNewsList.visibility = View.GONE
                        Toast.makeText(
                            this@NewsListActivity,
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

        progressBarNewsList.visibility = View.VISIBLE
        clNewsList.visibility = View.INVISIBLE
        call.clone().enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>?,
                response: Response<ArticlesResponse>?
            ) {
                if (response!!.isSuccessful())
                {
                    var newsListData: ArrayList<ArticlesListData>?=ArrayList()

                    response.body().data.map {
                        if (it.type.equals("news")) {
                            newsListData?.add(ArticlesListData(it.id,
                                it.title,
                                it.images,
                                it.type,
                                it.date))
//                            eventsListData=   arrayOf<ArticlesListData>(
//                                ArticlesListData(it.id,it.title, it.date, it.images,it.type))
                            recyclerViewNews = findViewById(R.id.rvNewsList)
                            val adapterEvents =
                                NewsListAdapter(newsListData, this@NewsListActivity)
                            recyclerViewNews.setHasFixedSize(true)
                            recyclerViewNews.layoutManager =
                                LinearLayoutManager(this@NewsListActivity,
                                    LinearLayoutManager.VERTICAL, false)
                        progressBarNewsList.visibility = View.GONE
                        clNewsList.visibility = View.VISIBLE
                            recyclerViewNews.adapter = adapterEvents
                        }
                    }
                }else {
                    progressBarNewsList.visibility = View.GONE
                    Toast.makeText(
                        this@NewsListActivity,
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