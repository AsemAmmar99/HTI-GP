package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.MyPreferences
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_student_profile.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        //hiding title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        //4second splash time
        Handler().postDelayed({
            //start main activity
            if (SharedPreferenceClass.loadString(this,"TOKEN").isEmpty()) {
                startActivity(Intent(this@SplashActivity, LoginScreen::class.java))
                finish()
            }else{
                getUser()
//                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
//                finish()
            }
            //finish this activity
        },2000)

    }
    fun getUser(){

        val retrofitUser = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${SharedPreferenceClass.loadString(this,"TOKEN")}").build()
                chain.proceed(request)
            }.build())
            .build()

        val clientUser = retrofitUser.create(Service::class.java)


        val call = clientUser.getUserData()
        call.clone().enqueue(object : Callback<UserDataResponce> {
            override fun onResponse(
                call: Call<UserDataResponce>,
                response: Response<UserDataResponce>
            ) {

                if (response.isSuccessful()){
                    var account_type= response.body().data?.account_type
                    if(account_type == "student"){
                        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                        finish()
                    }else if (account_type == "employee"){
                        startActivity(Intent(this@SplashActivity, StaffHomeActivity::class.java))
                        finish()
                    }else if (account_type == "supervisor"){
                        startActivity(Intent(this@SplashActivity, SupervisorHomeActivity::class.java))
                        finish()
                    }else{
                        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                        finish()
                    }

                }else{
                    Toast.makeText(baseContext, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SplashActivity, LoginScreen::class.java))
                    finish()
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


    }

}