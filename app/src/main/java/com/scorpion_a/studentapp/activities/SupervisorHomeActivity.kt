package com.scorpion_a.studentapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.SupervisorCardAdapter
import com.scorpion_a.studentapp.model.SupervisorCardData
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_student_profile.*
import kotlinx.android.synthetic.main.activity_supervisor_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SupervisorHomeActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervisor_home)

        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${SharedPreferenceClass.loadString(this,"TOKEN")}").build()
                chain.proceed(request)
            }.build())
            .build()

        val client = retrofit.create(Service::class.java)


        val call = client.getUserData()
        progressBarSuperHome.visibility = View.VISIBLE
        clSuperHome.visibility = View.INVISIBLE
        call.enqueue(object : Callback<UserDataResponce> {
            override fun onResponse(
                call: Call<UserDataResponce>,
                response: Response<UserDataResponce>
            ) {

                if (response.isSuccessful()){
                    if (tvSupervisorName != null) {
                        if (tvLogout.text == "Logout") {
                            tvSupervisorName.text = response.body().data?.name?.en
                        }else if (tvLogout.text == "تسجيل خروج") {
                            tvSupervisorName.text = response.body().data?.name?.ar
                        }
                        Picasso.with(baseContext).load(response.body().data?.image_path).into(ivSupervisorPP)
                        progressBarSuperHome.visibility = View.GONE
                        clSuperHome.visibility = View.VISIBLE
                    }
                }else{
                    progressBarSuperHome.visibility = View.GONE
                    clSuperHome.visibility = View.VISIBLE
                    Toast.makeText(this@SupervisorHomeActivity, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
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

        tvLogout.setOnClickListener {
            SharedPreferenceClass.clearSharedPrefrences(it.context)
            val intent = Intent(it.context, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }

        val supervisorCardItems: Array<SupervisorCardData> = arrayOf<SupervisorCardData>(
            SupervisorCardData(
                "12345",
                "Pending",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "657894",
                "Accepted",
                "Chemical Engineering",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "098763",
                "Delivered",
                "Economic",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "098253",
                "Rejected",
                "Business",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "13455",
                "Pending",
                "Economic Arabic",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "767543",
                "Accepted",
                "Civil Engineering",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "13425",
                "Pending",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "422525",
                "Pending",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "135766",
                "Delivered",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "139866",
                "Delivered",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "185766",
                "Delivered",
                "Business",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "135799",
                "Accepted",
                "Civil Engineering",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "245766",
                "Rejected",
                "Economic Arabic",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "135771",
                "Pending",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
        )

        recyclerView = findViewById(R.id.rvSupervisorCardItems)
        var adapter = SupervisorCardAdapter(supervisorCardItems)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}