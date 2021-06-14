package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.ViewRequestsListData
import com.scorpion_a.studentapp.model.requests.LoginRequests
import com.scorpion_a.studentapp.model.responses.LoginResponse
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_accepted_requests_details.*
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_pending_requests_details.*
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvCDateValue
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvCountValue
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvDEmailValue
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvIdValue
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvMoneyConfirmValue
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvNameInArabicValue
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvNameInEnglishValue
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvPriceValue
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvRequestDesc
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvRequestNumber
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvRequestStatusTitle
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvStudentStatusValue
import kotlinx.android.synthetic.main.activity_pending_requests_details.tvTotalPriceValue
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PendingRequestsDetailsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var receiptImage: ImageView
    lateinit var pending: ViewRequestsListData

    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_requests_details)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.prd)
        val gson = Gson()

        pending= gson.fromJson(intent.extras?.getString("Pending"), ViewRequestsListData::class.java)

        tvRequestNumber.text=pending.id
        tvRequestDesc.text=pending.request_type.name.en
        tvCountValue.text=pending.count
        tvPriceValue.text=pending.price +" LE"
        tvTotalPriceValue.text=pending.total_price+" LE"
        tvCDateValue.text=pending.created_at.split("T")[0]
        tvRequestStatusTitle.text=pending.status

        tvNameInArabicValue.text=pending.student.name.ar
        tvNameInEnglishValue.text=pending.student.name.en
        tvIdValue.text=pending.student_id
        tvStudentStatusValue.text=pending.student.account_type
        tvDEmailValue.text=pending.student.email
        tvMoneyConfirmValue.text="Confirmed"

        receiptImage = findViewById(R.id.ivReceipt)
        val image = ImageView(this)
        image.setImageResource(R.drawable.receipt)

        receiptImage.setOnClickListener{
            if (image.getParent() != null) (image.getParent() as ViewGroup).removeView(
                image
            )
           var dialog= AlertDialog.Builder(this).setPositiveButton(getString(R.string.ok),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        dialog?.dismiss()
                    }

                })

            dialog.setView(image).create().show()
            dialog.setView(image).create().window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        }

        buAccept.setOnClickListener {
            onAccept(it.context)
        }

        buReject.setOnClickListener {
            val intent = Intent(it.context, RejectReasonActivity::class.java)
            startActivity(intent)
        }



        val gsonl = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gsonl))
            .build()

        val service = retrofit.create(Service::class.java)
        val call = service.approveReq(pending.id,
           "PUT"
        )
        call.clone().enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>,
            ) {
                if (response.isSuccessful) {
                   finish()
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

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("test", t.toString())
            }
        })

        val callreq = service.rejectReq(pending.id,
            "PUT"
        )
        callreq.clone().enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>,
            ) {
                if (response.isSuccessful) {
                    finish()
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

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("test", t.toString())
            }
        })
    }

    private fun onAccept(context: Context) {

        val title = SpannableString(getString(R.string.pa))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.asr))
        message.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = androidx.appcompat.app.AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.yes)
            ) { dialog: DialogInterface, which: Int ->
                onAcceptYes(context)
                dialog.dismiss()
            }.setNegativeButton(
                getString(R.string.cancel)
            ) { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
            }
            .show()
            .window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }

    private fun onAcceptYes(context: Context) {

        val title = SpannableString(getString(R.string.thank_you))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.srhba))
        message.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = androidx.appcompat.app.AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.ok)
            ) { dialog: DialogInterface, which: Int ->
                val intent = Intent(context, StaffHomeActivity::class.java)
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            .show()
            .window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }





}