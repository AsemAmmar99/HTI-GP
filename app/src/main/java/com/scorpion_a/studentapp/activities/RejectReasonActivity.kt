package com.scorpion_a.studentapp.activities

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.gson.GsonBuilder
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.requests.ReqjectRequest
import com.scorpion_a.studentapp.model.responses.ActionsResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_accepted_requests_details.*
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_reject_reason.*
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RejectReasonActivity : BaseActivity() {
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reject_reason)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.rr)
       var reqId= intent.extras?.getString("id")
        Connection.isNetworkAvailable(this)

        val gsonl = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${
                SharedPreferenceClass.loadString(
                    this,
                    "TOKEN")
                }").build()
                chain.proceed(request)
            }.build())
            .build()



        val service = retrofit.create(Service::class.java)
        buConfirmRejection.setOnClickListener {
            if (etMessage.text.equals(null)){
                Toast.makeText(baseContext,
                    getString(R.string.taftr),
                    Toast.LENGTH_SHORT).show()
            }else {
                val callreq = service.rejectReq(
                    reqId!!,
                    ReqjectRequest("PUT",
                        etMessage.text.toString())
                )
                progressBarStRD.visibility = View.VISIBLE
                clStRD.visibility = View.INVISIBLE
                callreq.clone().enqueue(object : Callback<ActionsResponce> {
                    override fun onResponse(
                        call: Call<ActionsResponce>,
                        response: Response<ActionsResponce>,
                    ) {
                        if (response.isSuccessful) {
                            progressBarStRD.visibility = View.GONE
                            clStRD.visibility = View.VISIBLE
                            onReject()

                        } else {
                            progressBarStRD.visibility = View.GONE
                            clStRD.visibility = View.VISIBLE
                            Toast.makeText(baseContext,
                                getString(R.string.went_wrong),
                                Toast.LENGTH_SHORT).show()
                        }
                        // Catching Responses From Retrofit
                        Log.d("TAG", "onResponseisSuccessful: " + response.isSuccessful)
                        Log.d("TAG", "onResponsebody: " + response.body())
                        Log.d("TAG", "onResponseerrorBody: " + response.errorBody())
                        Log.d("TAG", "onResponsemessage: " + response.message())
                        Log.d("TAG", "onResponsecode: " + response.code())
                        Log.d("TAG", "onResponseheaders: " + response.headers())
                        Log.d("TAG", "onResponseraw: " + response.raw())
                        Log.d("TAG", "onResponsetoString: " + response.toString())


                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
//                    Toast.makeText(this@CheckoutActivity, jObjError.toString(), Toast.LENGTH_LONG).show()
                            Log.i("erroooooooooooooor", jObjError.toString())
                        } catch (e: Exception) {
//                    Toast.makeText(this@CheckoutActivity, e.message, Toast.LENGTH_LONG).show()
                        }


                        Log.d("fail", response.code().toString())

                    }

                    override fun onFailure(call: Call<ActionsResponce>, t: Throwable) {
                        Log.i("test", t.toString())
                    }
                })
            }
        }
    }

    private fun onReject() {

        val title = SpannableString(getString(R.string.pa))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.rsr))
        message.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.yes)
            ) { dialog: DialogInterface, which: Int ->
                onRejectYes()
                dialog.dismiss()
            }.setNegativeButton(
                getString(R.string.cancel)
            ) { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
            }
            .show()
            .window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }

    private fun onRejectYes() {

        val title = SpannableString(getString(R.string.thank_you))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.rhbr))
        message.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.ok)
            ) { dialog: DialogInterface, which: Int ->
                val intent = Intent(this, StaffHomeActivity::class.java)
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            .show()
            .window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }

}