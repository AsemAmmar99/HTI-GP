package com.scorpion_a.studentapp.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.activities.*
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tvDepartment
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_staff_home.*
import kotlinx.android.synthetic.main.fragment_staff_home.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StaffHomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_staff_home, container, false)

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
        view.progressBarSHome.visibility = View.VISIBLE
        view.clSHome.visibility = View.INVISIBLE
        call.enqueue(object : Callback<UserDataResponce> {
            override fun onResponse(
                call: Call<UserDataResponce>,
                response: Response<UserDataResponce>
            ) {

                if (response.isSuccessful()){
                    if (tvStaffDepartment != null) {
                        tvStaffDepartment.text="Department: " + response.body().data?.department
                        view.progressBarSHome.visibility = View.GONE
                        view.clSHome.visibility = View.VISIBLE
                    }
                }else{
                    view.progressBarSHome.visibility = View.GONE
                    view.clSHome.visibility = View.VISIBLE
                    Toast.makeText(view.context!! , getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
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

        view.clAccepted.setOnClickListener {
            val intent = Intent(it.context, AcceptedRequestsActivity::class.java)
            startActivity(intent)
        }

        view.clPending.setOnClickListener {
            val intent = Intent(it.context, PendingRequestsActivity::class.java)
            startActivity(intent)
        }

        view.clRejected.setOnClickListener {
            val intent = Intent(it.context, RejectedRequestsActivity::class.java)
            startActivity(intent)
        }

        view.clDelivered.setOnClickListener {
            val intent = Intent(it.context, DeliveredRequestsActivity::class.java)
            startActivity(intent)
        }

        view.clSearch.setOnClickListener {
            val intent = Intent(it.context, SearchForRequestsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        fun newInstance(): StaffHomeFragment = StaffHomeFragment()
    }
}