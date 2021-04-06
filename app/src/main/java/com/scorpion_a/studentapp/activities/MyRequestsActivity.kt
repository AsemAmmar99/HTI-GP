package com.scorpion_a.studentapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.ViewRequestsListAdapter
import com.scorpion_a.studentapp.model.ViewRequestsListData
import com.scorpion_a.studentapp.model.responses.MyRequestsResponse
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_requests_page.*
import kotlinx.android.synthetic.main.activity_my_requests_page.header
import kotlinx.android.synthetic.main.activity_student_profile.*
import kotlinx.android.synthetic.main.fragment_notification.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRequestsActivity : AppCompatActivity() {
    lateinit var  recyclerView: RecyclerView
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_requests_page)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.my_requests_page)

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
        progressBarMyReqs.visibility = View.VISIBLE
        clMyRequests.visibility = View.INVISIBLE
        call.enqueue(object : Callback<MyRequestsResponse> {
            override fun onResponse(
                call: Call<MyRequestsResponse>?,
                response: Response<MyRequestsResponse>?
            ) {
               if (response!!.isSuccessful())
               {
                   recyclerView= findViewById(R.id.rvViewRequests)
                   val adapter = ViewRequestsListAdapter(
                       response.body().data
                   )
                   progressBarMyReqs.visibility = View.GONE
                   clMyRequests.visibility = View.VISIBLE
                   recyclerView.setHasFixedSize(true)
                   recyclerView.layoutManager = LinearLayoutManager(baseContext)
                   recyclerView.adapter = adapter
               }else{
                   progressBarMyReqs.visibility = View.GONE
                   clMyRequests.visibility = View.VISIBLE
                   Toast.makeText(baseContext, "Something went wrong, please check your data", Toast.LENGTH_SHORT).show()
               }
            }

            override fun onFailure(call: Call<MyRequestsResponse>?, t: Throwable?) {
                Log.i("test", t.toString())
            }

        })

//        val viewRequestsListData: Array<ViewRequestsListData> = arrayOf<ViewRequestsListData>(
//            ViewRequestsListData(
//                "188148",
//                "Arabic Graduation certificate",
//                "Pending",
//                "2",
//                "4:00PM 4/4/2021"
//            ),
//            ViewRequestsListData(
//                "188149",
//                "English Graduation certificate zzzzzzzzzzzzzzzzzzz",
//                "Done",
//                "2",
//                "4:00PM 4/4/2021"
//            ),
//            ViewRequestsListData(
//                "188160",
//                "Proof of Enrollment",
//                "Missing Requirements",
//                "2",
//                "4:00PM 4/4/2021"
//            )
//        )


    }
}
