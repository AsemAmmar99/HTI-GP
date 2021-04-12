package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.gson.GsonBuilder
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.requests.LoginRequests
import com.scorpion_a.studentapp.model.requests.UpdateUserRequests
import com.scorpion_a.studentapp.model.responses.LoginResponse
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_student_edit_profile.*
import kotlinx.android.synthetic.main.activity_student_edit_profile.buSave
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentEditProfile : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_edit_profile)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.editing_profile)

        etSArabName.setText(intent.getStringExtra("ar").toString())
        etSEnglishName.setText(intent.getStringExtra("en").toString())
        etSEmail.setText(intent.getStringExtra("email").toString())
        etSMobile.setText(intent.getStringExtra("phone").toString())

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${SharedPreferenceClass.loadString(this,"TOKEN")}").build()
                chain.proceed(request)
            }.build())
            .build()

        val service = retrofit.create(Service::class.java)

        buSave.setOnClickListener {
            if (etSArabName.text.trim().toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.peyan), Toast.LENGTH_SHORT).show()
            }else if(etSEnglishName.text.trim().toString().isEmpty()){
                Toast.makeText(this, getString(R.string.peyen), Toast.LENGTH_SHORT).show()
            }else if(etSEmail.text.trim().toString().isEmpty()){
            Toast.makeText(this, getString(R.string.peye), Toast.LENGTH_SHORT).show()
        }else if(etSMobile.text.trim().toString().isEmpty()){
            Toast.makeText(this, getString(R.string.peypn), Toast.LENGTH_SHORT).show()
        }else {
                val call = service.updateUser(
                    UpdateUserRequests(
                        etSArabName.text.trim().toString(),
                        etSEnglishName.text.trim().toString(),
                        etSMobile.text.trim().toString(),
                        etSEmail.text.trim().toString()
                    )
                )
                progressBarEditProfile.visibility = View.VISIBLE
                clEditProfilePage.visibility = View.INVISIBLE
                call.enqueue(object : Callback<UserDataResponce> {
                    override fun onResponse(
                        call: Call<UserDataResponce>,
                        response: Response<UserDataResponce>
                    ) {
                        if (response.isSuccessful()){
                            progressBarEditProfile.visibility = View.GONE
                            val intent = Intent(it.context, StudentProfile::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            progressBarEditProfile.visibility = View.GONE
                            clEditProfilePage.visibility = View.VISIBLE
                            Toast.makeText(baseContext, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
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

                    override fun onFailure(call: Call<UserDataResponce>, t: Throwable) {
                        Log.i("test", t.toString())
                    }
                })
            }

        }
    }
}