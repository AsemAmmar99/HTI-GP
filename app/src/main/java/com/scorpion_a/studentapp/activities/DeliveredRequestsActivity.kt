package com.scorpion_a.studentapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.StaffRequestsListAdapter
import com.scorpion_a.studentapp.model.ViewRequestsListData
import com.scorpion_a.studentapp.model.responses.MyRequestsResponse
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_delivered_requests.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class DeliveredRequestsActivity : BaseActivity() {
    lateinit var toolbar: Toolbar
    lateinit var staffRequestsListData: ArrayList<ViewRequestsListData>
    lateinit var adapterDelivered: StaffRequestsListAdapter
    lateinit var rvStaffRequestD: RecyclerView
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivered_requests)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.delivered_requests)
        Connection.isNetworkAvailable(this)

        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${SharedPreferenceClass.loadString(this,"TOKEN")}").build()
                chain.proceed(request)
            }.build())
            .build()


        val client = retrofit.create(Service::class.java)

        val call = client.getRequestsData()

        mSwipeRefreshLayout= findViewById(R.id.swipe_refresh_layout)
        mSwipeRefreshLayout!!.setOnRefreshListener {
            call.clone().enqueue(object : Callback<MyRequestsResponse> {
                override fun onResponse(
                    call: Call<MyRequestsResponse>,
                    response: Response<MyRequestsResponse>
                ) {

                    if (response.isSuccessful()){
                        mSwipeRefreshLayout!!.isRefreshing = false
                        var stfrequestsListData: java.util.ArrayList<ViewRequestsListData>?=ArrayList()
                        response.body().data.map {
                            if (it.status.equals("done")) {
                                stfrequestsListData?.add(ViewRequestsListData(it.id,
                                    it.name,
                                    it.price,
                                    it.status,it.count,it.total_price,it.created_at,it.student_id,it.student,it.request_type,it.receipt))
                                progressBarStD.visibility = GONE
                                clStD.visibility = VISIBLE
//                            eventsListData=   arrayOf<ArticlesListData>(
//                                ArticlesListData(it.id,it.title, it.date, it.images,it.type))

                            }
                        }
                        rvStaffRequestD = findViewById(R.id.rvStaffRequestD)
                        Collections.sort(stfrequestsListData, DescendingComparator())
                        adapterDelivered.notifyDataSetChanged();
                        adapterDelivered = StaffRequestsListAdapter(stfrequestsListData,
                            this@DeliveredRequestsActivity,
                            "delivered")
                        rvStaffRequestD.setHasFixedSize(true)
                        rvStaffRequestD.layoutManager =
                            LinearLayoutManager(this@DeliveredRequestsActivity)
                        rvStaffRequestD.adapter = adapterDelivered
                    }else{
                        mSwipeRefreshLayout!!.isRefreshing = false
                        progressBarStD.visibility = GONE
                        clStD.visibility = VISIBLE
                        Toast.makeText(this@DeliveredRequestsActivity, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
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



                override fun onFailure(call: Call<MyRequestsResponse>?, t: Throwable?) {
                    Log.i("test", t.toString())
                }
            })
        }
        progressBarStD.visibility = VISIBLE
        clStD.visibility = INVISIBLE
        call.clone().enqueue(object : Callback<MyRequestsResponse> {
            override fun onResponse(
                call: Call<MyRequestsResponse>,
                response: Response<MyRequestsResponse>
            ) {

                if (response.isSuccessful()){
                    var stfrequestsListData: java.util.ArrayList<ViewRequestsListData>?=ArrayList()
                    response.body().data.map {
                        if (it.status.equals("done")) {
                            stfrequestsListData?.add(ViewRequestsListData(it.id,
                                it.name,
                                it.price,
                                it.status,it.count,it.total_price,it.created_at,it.student_id,it.student,it.request_type,it.receipt))
                            progressBarStD.visibility = GONE
                            clStD.visibility = VISIBLE
//                            eventsListData=   arrayOf<ArticlesListData>(
//                                ArticlesListData(it.id,it.title, it.date, it.images,it.type))

                        }
                    }
                    rvStaffRequestD = findViewById(R.id.rvStaffRequestD)
                    Collections.sort(stfrequestsListData, DescendingComparator())

                    adapterDelivered = StaffRequestsListAdapter(stfrequestsListData,
                        this@DeliveredRequestsActivity,
                        "delivered")
                    rvStaffRequestD.setHasFixedSize(true)
                    rvStaffRequestD.layoutManager =
                        LinearLayoutManager(this@DeliveredRequestsActivity)
                    rvStaffRequestD.adapter = adapterDelivered
                }else{
                    progressBarStD.visibility = GONE
                    clStD.visibility = VISIBLE
                    Toast.makeText(this@DeliveredRequestsActivity, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
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



            override fun onFailure(call: Call<MyRequestsResponse>?, t: Throwable?) {
                Log.i("test", t.toString())
            }
        })


//        staffRequestsListData = ArrayList<StaffRequestsListData>()
//        staffRequestsListData.add(StaffRequestsListData(
//            "123"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "123"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "245"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "3567"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "44444"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "6666"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "333333"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "37252"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "12734"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "421098"
//        ))
//        staffRequestsListData.add(StaffRequestsListData(
//            "245213"
//        ))
//
//        val adapterDelivered = StaffRequestsListAdapter(staffRequestsListData, this, "delivered")
//        rvStaffRequestD.setHasFixedSize(true)
//        rvStaffRequestD.layoutManager = LinearLayoutManager(this)
//        rvStaffRequestD.adapter = adapterDelivered

    }
}

class DescendingComparator : Comparator<ViewRequestsListData?> {


    override fun compare(o1: ViewRequestsListData?, o2: ViewRequestsListData?): Int {
        //Log.d("hightolow","productList"+p2.getPrice().compareTo(p1.getPrice()));
        //  Log.d("hightolow","productList:"+p1.getPrice());
        //    Log.d("hightolow","productList::"+p2.getPrice());
        val pM1 = Math.round(o1!!.id.toFloat()).toInt()
        val pM2 = Math.round(o2!!.id.toFloat()).toInt()
        return if (pM2 > pM1) {
            1
        } else if (pM2 < pM1) {
            -1
        } else {
            0
        }
    }
}