package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
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
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.ViewRequestsListData
import com.scorpion_a.studentapp.model.responses.ActionsResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_accepted_requests_details.*
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AcceptedRequestsDetailsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var receiptImage: ImageView
    lateinit var Accepted: ViewRequestsListData
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accepted_requests_details)
        val gson = Gson()

        toolbar = header.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.ard)
        Accepted= gson.fromJson(intent.extras?.getString("Accepted"), ViewRequestsListData::class.java)

        tvRequestNumber.text=Accepted.id
        tvRequestDesc.text=Accepted.request_type.name.en
        tvCountValue.text=Accepted.count
        tvPriceValue.text=Accepted.price +" LE"
        tvTotalPriceValue.text=Accepted.total_price+" LE"
        tvCDateValue.text=Accepted.created_at.split("T")[0]
        tvRequestStatusTitle.text=Accepted.status

        tvNameInArabicValue.text=Accepted.student.name.ar
        tvNameInEnglishValue.text=Accepted.student.name.en
        tvIdValue.text=Accepted.student_id
        tvStudentStatusValue.text=Accepted.student.account_type
        tvDEmailValue.text=Accepted.student.email
        tvMoneyConfirmValue.text="Confirmed"
        receiptImage = findViewById(R.id.ivReceipt)
        val image = ImageView(this)
        image.setImageResource(R.drawable.receipt)

        receiptImage.setOnClickListener {
            if (image.getParent() != null) (image.getParent() as ViewGroup).removeView(
                image
            )
            var dialog = AlertDialog.Builder(this).setPositiveButton(getString(R.string.ok),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        dialog?.dismiss()
                    }

                })

            dialog.setView(image).create().show()
        }

        val gsonl = GsonBuilder()
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
        buDone.setOnClickListener {

            val callreq = service.doneReq(Accepted.id,
                "PUT"
            )
            callreq.clone().enqueue(object : Callback<ActionsResponce> {
                override fun onResponse(
                    call: Call<ActionsResponce>,
                    response: Response<ActionsResponce>,
                ) {
                    if (response.isSuccessful) {
                        onDone()
                    } else {

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

                }

                override fun onFailure(call: Call<ActionsResponce>, t: Throwable) {
                    Log.i("test", t.toString())
                }
            })
        }

    }

    private fun onDone() {

        val title = SpannableString(getString(R.string.thank_you))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.srhbmad))
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