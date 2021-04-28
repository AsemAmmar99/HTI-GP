package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.responses.ArticleDetailsResponse
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.activity_staff_profile_page.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        var id= intent.getStringExtra("id")

        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${SharedPreferenceClass.loadString(this,"TOKEN")}").build()
                chain.proceed(request)
            }.build())
            .build()

        val client = retrofit.create(Service::class.java)

        Log.i("idsssssss",id)
        val call = client.getArticleDetails(id)
        progressBarArticleDetails.visibility = View.VISIBLE
        clArticleDetails.visibility = View.INVISIBLE
        call.enqueue(object : Callback<ArticleDetailsResponse> {
            override fun onResponse(
                call: Call<ArticleDetailsResponse>,
                response: Response<ArticleDetailsResponse>
            ) {

                if (response.isSuccessful()){
                    tvNewsTitle.text = response.body()?.data?.title
                    tvNewsDetails.text = response.body()?.data?.description
                    tvNewsDate.text =response.body()?.data?.date
                    progressBarArticleDetails.visibility = View.GONE
                    clArticleDetails.visibility = View.VISIBLE
                }else{
                    progressBarArticleDetails.visibility = View.GONE
                    clArticleDetails.visibility = View.VISIBLE
                    Toast.makeText(this@NewsDetailsActivity, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
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



            override fun onFailure(call: Call<ArticleDetailsResponse>?, t: Throwable?) {
                Log.i("test", t.toString())
            }
        })
    }
}