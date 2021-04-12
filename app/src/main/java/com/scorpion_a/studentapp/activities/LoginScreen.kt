package com.scorpion_a.studentapp.activities

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.google.gson.GsonBuilder
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.fragments.ResetPasswordFragment
import com.scorpion_a.studentapp.model.requests.LoginRequests
import com.scorpion_a.studentapp.model.responses.LoginResponse
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.network.Service.Companion.BaseUrl
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Lang.Companion.loadLocate
import com.scorpion_a.studentapp.utils.Lang.Companion.setLocate
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_student_profile.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class LoginScreen : AppCompatActivity() {
    var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        lang.setOnClickListener {
            if(lang.text == "English") {
                setLocate("en", it.context)
                recreate()
            }else if(lang.text == "العربية") {
                setLocate("ar", it.context)
                recreate()
            }
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


        val service = retrofit.create(Service::class.java)

        buLogin.setOnClickListener {
            if (etID.text.trim().toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.please_id), Toast.LENGTH_SHORT).show()
            }else if(etPassword.text.trim().toString().isEmpty()){
                Toast.makeText(this, getString(R.string.please_password), Toast.LENGTH_SHORT).show()
            }else {
                val call = service.getLoginData(
                    LoginRequests(
                        Integer.valueOf(etID.text.trim().toString()),
                        etPassword.text.trim().toString()
                    )
                )
                progressBarLogin.visibility = View.VISIBLE
                clLogin.visibility = View.INVISIBLE
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful()){
                            Log.i("call", response.body().token.toString())
                            SharedPreferenceClass.saveString(it.context,"TOKEN",response.body().token.toString())
                            progressBarLogin.visibility = View.GONE
                            getUser()
                        }else{
                            progressBarLogin.visibility = View.GONE
                            clLogin.visibility = View.VISIBLE
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

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.i("test", t.toString())
                    }
                })
            }

        }


        tvHelp.setOnClickListener {
            val intent = Intent(this, AboutHTIActivity::class.java)
            startActivity(intent)
        }

        tvForgot.setOnClickListener {
            onForgot(it.context)
        }





//        tabLayout = findViewById(R.id.tabs)
//        tabLayout!!.addTab(tabLayout!!.newTab().setText("English"))
//        tabLayout!!.addTab(tabLayout!!.newTab().setText("العربية"))
//        setTabBG(
//            R.drawable.tab_left_select,
//            R.drawable.tab_right_unselect
//        )


//        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                if (tabLayout!!.getSelectedTabPosition() === 0) {
//                    setTabBG(
//                        R.drawable.tab_left_select,
//                        R.drawable.tab_right_unselect
//                    )
//                } else {
//                    setTabBG(
//                        R.drawable.tab_left_unselect,
//                        R.drawable.tab_right_select
//                    )
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//        })


        tvHTI.setText("")
        tvHTI.setCharacterDelay(80)
        tvHTI.animateText(getString(R.string.higher_technological_institute))

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
        progressBarLogin.visibility = View.VISIBLE
        clLogin.visibility = View.INVISIBLE
        call.enqueue(object : Callback<UserDataResponce> {
            override fun onResponse(
                call: Call<UserDataResponce>,
                response: Response<UserDataResponce>
            ) {

                if (response.isSuccessful()){
                    var account_type= response.body().data?.account_type
                    progressBarLogin.visibility = View.GONE
                    if(account_type == "student"){
                        startActivity(Intent(this@LoginScreen, HomeActivity::class.java))
                        finish()
                    }else if (account_type == "employee"){
                        startActivity(Intent(this@LoginScreen, StaffHomeActivity::class.java))
                        finish()
                    }else if (account_type == "supervisor"){
                        startActivity(Intent(this@LoginScreen, SupervisorHomeActivity::class.java))
                        finish()
                    }else{
                        startActivity(Intent(this@LoginScreen, HomeActivity::class.java))
                        finish()
                    }
                }else{
                    progressBarLogin.visibility = View.GONE
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

            override fun onFailure(call: Call<UserDataResponce>?, t: Throwable?) {
                Log.i("test", t.toString())
            }
        })


    }

    private fun setTabBG(tab1: Int, tab2: Int) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val tabStrip = tabLayout!!.getChildAt(0) as ViewGroup
            val tabView1 = tabStrip.getChildAt(0)
            val tabView2 = tabStrip.getChildAt(1)
            if (tabView1 != null) {
                val paddingStart = tabView1.getPaddingStart()
                val paddingTop = tabView1.getPaddingTop()
                val paddingEnd = tabView1.getPaddingEnd()
                val paddingBottom = tabView1.getPaddingBottom()
                ViewCompat.setBackground(
                    tabView1,
                    AppCompatResources.getDrawable(tabView1.getContext(), tab1)
                )
                ViewCompat.setPaddingRelative(
                    tabView1,
                    paddingStart,
                    paddingTop,
                    paddingEnd,
                    paddingBottom
                )
            }
            if (tabView2 != null) {
                val paddingStart = tabView2.getPaddingStart()
                val paddingTop = tabView2.getPaddingTop()
                val paddingEnd = tabView2.getPaddingEnd()
                val paddingBottom = tabView2.getPaddingBottom()
                ViewCompat.setBackground(
                    tabView2,
                    AppCompatResources.getDrawable(tabView2.getContext(), tab2)
                )
                ViewCompat.setPaddingRelative(
                    tabView2,
                    paddingStart,
                    paddingTop,
                    paddingEnd,
                    paddingBottom
                )
            }
        }
    }
    private fun onForgot(context: Context) {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.attention))
            .setMessage(getString(R.string.please_send_id))
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.send_email)
            ) { dialog: DialogInterface, which: Int ->
                try {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "info@hti.edu.eg"))
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Question")

                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    //TODO smth
                }
                finish()
                dialog.dismiss()
            } .setNegativeButton(
                getString(R.string.ok)
            ) { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
            }
            .show()
//        val bottomSheetDialog: ResetPasswordFragment = ResetPasswordFragment.newInstance()
//        bottomSheetDialog.show(supportFragmentManager, "Bottom Sheet Dialog Fragment")
    }
}
