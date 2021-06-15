package com.scorpion_a.studentapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.EventsListAdapter
import com.scorpion_a.studentapp.adapters.StaffRequestsListAdapter
import com.scorpion_a.studentapp.model.ArticlesListData
import com.scorpion_a.studentapp.model.StaffRequestsListData
import com.scorpion_a.studentapp.model.ViewRequestsListData
import com.scorpion_a.studentapp.model.responses.MyRequestsResponse
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_search_for_requests.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchForRequestsActivity : AppCompatActivity(){
    lateinit var toolbar: Toolbar
    lateinit var simpleSearchView: SearchView
    lateinit var stfrequestsListData: ArrayList<ViewRequestsListData>
    lateinit var adapterSearch: StaffRequestsListAdapter
    lateinit var rvStaffRequestS: RecyclerView
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for_requests)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.search_for_request)
        stfrequestsListData=ArrayList<ViewRequestsListData>()

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
//                    var stfrequestsListData: java.util.ArrayList<ViewRequestsListData>?=ArrayList()
                        response.body().data.map {
                            stfrequestsListData?.add( ViewRequestsListData(it.id,
                                it.name,
                                it.price,
                                it.status,it.count,it.total_price,it.created_at,it.student_id,it.student,it.request_type))
                            progressBarStS.visibility = GONE
                            clStS.visibility = VISIBLE
//                            eventsListData=   arrayOf<ArticlesListData>(
//                                ArticlesListData(it.id,it.title, it.date, it.images,it.type))
                            rvStaffRequestS = findViewById(R.id.rvStaffRequestS)
                            adapterSearch = StaffRequestsListAdapter(stfrequestsListData, this@SearchForRequestsActivity, "search")
                            rvStaffRequestS.setHasFixedSize(true)
                            rvStaffRequestS.layoutManager = LinearLayoutManager(this@SearchForRequestsActivity)
                            rvStaffRequestS.adapter = adapterSearch}
                    }else{
                        mSwipeRefreshLayout!!.isRefreshing = false
                        progressBarStS.visibility = GONE
                        clStS.visibility = VISIBLE
                        Toast.makeText(this@SearchForRequestsActivity, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
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
        progressBarStS.visibility = VISIBLE
        clStS.visibility = INVISIBLE
        call.clone().enqueue(object : Callback<MyRequestsResponse> {
            override fun onResponse(
                call: Call<MyRequestsResponse>,
                response: Response<MyRequestsResponse>
            ) {

                if (response.isSuccessful()){
//                    stfrequestsListData: java.util.ArrayList<ViewRequestsListData>?=ArrayList()
//                    var stfrequestsListData: java.util.ArrayList<ViewRequestsListData>?=ArrayList()
                    response.body().data.map {
                            stfrequestsListData?.add( ViewRequestsListData(it.id,
                                it.name,
                                it.price,
                                it.status,it.count,it.total_price,it.created_at,it.student_id,it.student,it.request_type))
                            progressBarStS.visibility = GONE
                            clStS.visibility = VISIBLE
//                            eventsListData=   arrayOf<ArticlesListData>(
//                                ArticlesListData(it.id,it.title, it.date, it.images,it.type))
                        rvStaffRequestS = findViewById(R.id.rvStaffRequestS)
                            adapterSearch = StaffRequestsListAdapter(stfrequestsListData, this@SearchForRequestsActivity, "search")
                            rvStaffRequestS.setHasFixedSize(true)
                            rvStaffRequestS.layoutManager = LinearLayoutManager(this@SearchForRequestsActivity)
                            rvStaffRequestS.adapter = adapterSearch}
                }else{
                    progressBarStS.visibility = GONE
                    clStS.visibility = VISIBLE
                    Toast.makeText(this@SearchForRequestsActivity, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
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
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "123"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "123"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "245"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "3567"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "44444"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "6666"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "333333"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "37252"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "12734"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "421098"
//            )
//        )
//        staffRequestsListData.add(
//            StaffRequestsListData(
//                "245213"
//            )
//        )
//        adapterSearch = StaffRequestsListAdapter(staffRequestsListData, this, "search")
//        rvStaffRequestS.setHasFixedSize(true)
//        rvStaffRequestS.layoutManager = LinearLayoutManager(this)
//        rvStaffRequestS.adapter = adapterSearch
        etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })
//        etSearch.addTextChangedListener(object: TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                adapterSearch.filter.filter(s.toString())
//            }
//
//            override fun afterTextChanged(s: Editable?) {
////                Log.i("search", s.toString())
////                filter(s.toString())
//            }
//        })


    }
    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<ViewRequestsListData> = ArrayList()

        // running a for loop to compare elements.
        for (item in stfrequestsListData) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.id  .toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapterSearch.filterList(filteredlist)
        }
    }

//    override fun onQueryTextSubmit(query: String?): Boolean {
//        return false
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//        var text = newText!!
//        adapterSearch.filter(text)
//        return false
//    }

//    private fun filter(filterItem: String) {
//
//        var tempList: ArrayList<StaffRequestsListData> = ArrayList()
//
//        for (d in tempList) {
//
//            if (filterItem in d.requestNumVal.toString()) {
//                tempList.add(d)
//            }
//        }
//
//        adapterSearch.filterList(tempList)
//    }
}