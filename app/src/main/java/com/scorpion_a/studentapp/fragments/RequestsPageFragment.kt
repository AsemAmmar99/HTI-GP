package com.scorpion_a.studentapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.activities.RequestTutorialActivity
import com.scorpion_a.studentapp.activities.SupportActivity
import com.scorpion_a.studentapp.adapters.RequestListAdapter
import com.scorpion_a.studentapp.adapters.ViewRequestsListAdapter
import com.scorpion_a.studentapp.model.RequestListData
import com.scorpion_a.studentapp.model.responses.MyRequestsResponse
import com.scorpion_a.studentapp.model.responses.RequestsResponse
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import kotlinx.android.synthetic.main.fragment_notification.view.*
import kotlinx.android.synthetic.main.fragment_notification.view.header
import kotlinx.android.synthetic.main.fragment_requests_page.*
import kotlinx.android.synthetic.main.fragment_requests_page.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestsPageFragment : Fragment() {
    lateinit var toolbar: Toolbar
    var tabLayout: TabLayout?= null
    lateinit var tvToturial: TextView

    lateinit var  recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_requests_page, container, false)
        toolbar=view.header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.request_page)


//        val requestListData: Array<RequestListData> = arrayOf<RequestListData>(
//            RequestListData(
//                "Arabic Graduation certificate","30"
//            ),
//            RequestListData(
//                "English Graduation certificate","50"
//            ),
//            RequestListData(
//                "Arab equivalent certificate","70"
//            ),
//            RequestListData(
//                "Certificate of good conduct","100"
//            ),
//            RequestListData(
//                "Arabic estimate statement","30"
//            ),
//            RequestListData(
//                "Arabic Graduation certificate","20"
//            ),
//            RequestListData(
//                "English statement of estimates","30"
//            ),
//            RequestListData(
//                "Proof of enrollment","50"
//            ),
//            RequestListData(
//                "English Certificate of study materials","40"
//            ),
//            RequestListData(
//                "Identifying Card","30"
//            ),
//            RequestListData(
//                "Recommendation Letter","40"
//            )
//        )
        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${SharedPreferenceClass.loadString(context,"TOKEN")}").build()
                chain.proceed(request)
            }.build())
            .build()

        val client = retrofit.create(Service::class.java)
        val call = client.getRequestsTypesData()
        view.progressBarRequests.visibility = View.VISIBLE
        view.clRequests.visibility = View.INVISIBLE
        call.enqueue(object : Callback<RequestsResponse> {
            override fun onResponse(
                call: Call<RequestsResponse>?,
                response: Response<RequestsResponse>?
            ) {
                if (response!!.isSuccessful())
                {
                    recyclerView = view.findViewById(R.id.rvRequest)
                    val adapter = RequestListAdapter(response.body().data, context)
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(view.context,
                        LinearLayoutManager.VERTICAL,false)
                    view.progressBarRequests.visibility = View.GONE
                    view.clRequests.visibility = View.VISIBLE
                    recyclerView.adapter = adapter
                    tvToturial=view.findViewById(R.id.request_tutorial)
                    tvToturial.setOnClickListener {
                        val intent = Intent(context, RequestTutorialActivity::class.java)
                        startActivity(intent)
                    }
                }else {
                    view.progressBarRequests.visibility = View.GONE
                    Toast.makeText(
                        context,
                        getString(R.string.went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<RequestsResponse>?, t: Throwable?) {
                Log.i("test", t.toString())
            }


        })

        return view
    }
    companion object {
        fun newInstance(): RequestsPageFragment = RequestsPageFragment()
    }
}