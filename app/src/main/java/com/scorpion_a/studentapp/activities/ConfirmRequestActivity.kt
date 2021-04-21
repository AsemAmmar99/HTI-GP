package com.scorpion_a.studentapp.activities

import android.app.Activity
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
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.github.dhaval2404.imagepicker.ImagePicker
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.requests.RequestRequests
import com.scorpion_a.studentapp.model.responses.LoginResponse
import com.scorpion_a.studentapp.model.responses.SubmitRequestResponse
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import kotlinx.android.synthetic.main.activity_confirm_request.*
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_request_data.*
import kotlinx.android.synthetic.main.activity_student_profile.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class ConfirmRequestActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    var rCount:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_request)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.confirm_request)
        tvRequestDesc.text=intent.getStringExtra("desc");
        tvRequestCount.text=intent.getStringExtra("count");
        tvRequestTotalPrice.text=intent.getStringExtra("total");
        rCount=intent.getStringExtra("count").toString().toInt()

        tvEditInfo.setOnClickListener {
            val intent = Intent(it.context, StudentEditProfile::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(Service.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${SharedPreferenceClass.loadString(this,"TOKEN")}").build()
                chain.proceed(request)
            }.build())
            .build()

        val client = retrofit.create(Service::class.java)
        val call = client.submitRequest(RequestRequests(intent.getStringExtra("id"),rCount))


        buConfirm.setOnClickListener {
            progressBarConfirm.visibility = View.VISIBLE
            clConfirmRequest.visibility = View.INVISIBLE
            call.enqueue(object : Callback<SubmitRequestResponse> {
                override fun onResponse(
                    call: Call<SubmitRequestResponse>?,
                    response: Response<SubmitRequestResponse>?
                ) {
                  if( response!!.isSuccessful()){
                      Log.d("TAG", "response.body().data.id" + response.body().data.id);
                      progressBarConfirm.visibility = View.GONE
                      clConfirmRequest.visibility = View.VISIBLE
                      onRequestSent(it.context  ,response.body().data.id)
                  }else{
                      progressBarConfirm.visibility = View.GONE
                      clConfirmRequest.visibility = View.VISIBLE
                      Toast.makeText(baseContext, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
                  }

                    Log.d("TAG", "onResponseisSuccessful: " + response.isSuccessful());
                    Log.d("TAG", "onResponsebody: " + response.body());
                    Log.d("TAG", "onResponseerrorBody: " + response.errorBody());
                    Log.d("TAG", "onResponsemessage: " + response.message());
                    Log.d("TAG", "onResponsecode: " + response.code());
                    Log.d("TAG", "onResponseheaders: " + response.headers());
                    Log.d("TAG", "onResponseraw: " + response.raw());
                    Log.d("TAG", "onResponsetoString: " + response.toString());
                }


                override fun onFailure(call: Call<SubmitRequestResponse>?, t: Throwable?) {
                    Log.i("test", t.toString())

                }

            })

        }
        buUploadReceipt.setOnClickListener{
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data
            ivReceipt.setImageURI(fileUri)

            //You can get File object from intent
            val file: File = ImagePicker.getFile(data)!!

            //You can also get File Path from intent
            val filePath:String = ImagePicker.getFilePath(data)!!
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.task_cancelled), Toast.LENGTH_SHORT).show()
        }
    }
    private fun onRequestSent(context: Context,rId:String) {

        val title = SpannableString(getString(R.string.thank))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.yrequest_number)+rId+getString(R.string.succes_done))
        message.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog: DialogInterface, which: Int ->
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//                finish()
                val intent = Intent(this, HomeActivity::class.java)
//                intent.putExtra("menu", 1)
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            .show()
            .window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }
}