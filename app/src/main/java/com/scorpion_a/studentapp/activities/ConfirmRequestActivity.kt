package com.scorpion_a.studentapp.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.dhaval2404.imagepicker.ImagePicker
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.requests.RequestRequests
import com.scorpion_a.studentapp.model.responses.SubmitRequestResponse
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import kotlinx.android.synthetic.main.activity_confirm_request.*
import kotlinx.android.synthetic.main.activity_confirm_request.tvDepartment
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.properties.Delegates

class ConfirmRequestActivity : BaseActivity() {
    lateinit var toolbar: Toolbar
    var rCount:Int = 0
    var image1 by Delegates.notNull<Boolean>()
    var image2 by Delegates.notNull<Boolean>()
    var image3 by Delegates.notNull<Boolean>()
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    var imagesArray :ArrayList<MultipartBody.Part>? = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_request)

        Connection.isNetworkAvailable(this)

        sharedPreferences =
            this.getSharedPreferences("images", MODE_PRIVATE)
        editor = sharedPreferences.edit()


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

//        clReceipt1.setOnClickListener {
//            ImagePicker.with(this)
//                .crop()	    			//Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                .start()
//        }
//
//        clReceipt2.setOnClickListener {
//            ImagePicker.with(this)
//                .crop()	    			//Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                .start()
//        }
//
//        clReceipt3.setOnClickListener {
//            ImagePicker.with(this)
//                .crop()	    			//Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                .start()
//        }

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

        val client = retrofit.create(Service::class.java)
        val request_type_id = RequestBody.create(MediaType.parse("multipart/form-data"), intent.getStringExtra("id"))
        val rCount_ = RequestBody.create(MediaType.parse("multipart/form-data"), rCount.toString())
        val call = client.submitRequest(request_type_id, rCount_,
            imagesArray!!)

        val callStudent = client.getUserData()
        progressBarConfirm.visibility = View.VISIBLE
        clConfirmRequest.visibility = View.INVISIBLE
        callStudent.clone().enqueue(object : Callback<UserDataResponce> {
            override fun onResponse(
                call: Call<UserDataResponce>,
                response: Response<UserDataResponce>
            ) {

                if (response.isSuccessful()) {
                    if (tvDepartment != null) {
                        tvNameInEnglishValue.text = response.body().data?.name?.en
                        tvNameInArabicValue.text = response.body().data?.name?.ar
                        tvDepartmentValue.text = response.body().data?.department
                        tvStatusValue.text = response.body().data?.account_type
                        tvIdValue.text = response.body().data?.user_id

                            progressBarConfirm.visibility = View.GONE
                            clConfirmRequest.visibility = View.VISIBLE
                    }
                } else {
                    progressBarConfirm.visibility = View.GONE
                    clConfirmRequest.visibility = View.VISIBLE
                    Toast.makeText(this@ConfirmRequestActivity, getString(R.string.went_wrong), Toast.LENGTH_SHORT)
                        .show()
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


        buConfirm.setOnClickListener {
            if (imagesArray!!.size == 0){
                Toast.makeText(this, getString(R.string.upal), Toast.LENGTH_SHORT).show()
            }else{
                progressBarConfirm.visibility = View.VISIBLE
                clConfirmRequest.visibility = View.INVISIBLE
                Log.i("id", (intent.getStringExtra("id")))
                Log.i("count", rCount.toString())
                Log.i("imageArray", imagesArray!!.toString())
                call.clone().enqueue(object : Callback<SubmitRequestResponse> {
                    override fun onResponse(
                        call: Call<SubmitRequestResponse>?,
                        response: Response<SubmitRequestResponse>?,
                    ) {
                        if (response!!.isSuccessful()) {
//                        Log.d("TAG", "response.body().data.id" + response.body().data.id);
                            progressBarConfirm.visibility = View.GONE
                            clConfirmRequest.visibility = View.VISIBLE
//                        onRequestSent(it.context, response.body().data.id)
                        } else {
                            progressBarConfirm.visibility = View.GONE
                            clConfirmRequest.visibility = View.VISIBLE
                            Toast.makeText(baseContext,
                                getString(R.string.went_wrong),
                                Toast.LENGTH_SHORT).show()
                        }

                        Log.d("TAG", "onResponseisSuccessful: " + response.isSuccessful());
                        Log.d("TAG", "onResponsebody: " + response.body());
                        Log.d("TAG", "onResponseerrorBody: " + response.errorBody());
                        Log.d("TAG", "onResponsemessage: " + response.message());
                        Log.d("TAG", "onResponsecode: " + response.code());
                        Log.d("TAG", "onResponseheaders: " + response.headers());
                        Log.d("TAG", "onResponseraw: " + response.raw());
                        Log.d("TAG", "onResponsetoString: " + response.toString());

                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            Toast.makeText(this@ConfirmRequestActivity,
                                jObjError.toString(),
                                Toast.LENGTH_LONG).show()
                            Log.i("erroooooooooooooor", jObjError.toString())
                        } catch (e: Exception) {
                            Toast.makeText(this@ConfirmRequestActivity,
                                e.message,
                                Toast.LENGTH_LONG).show()
                        }
                    }


                    override fun onFailure(call: Call<SubmitRequestResponse>?, t: Throwable?) {
                        Log.i("test", t.toString())

                    }

                })
            }

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
            if (sharedPreferences.getString("imageFile1", null) != null){
                ivReceipt1.setImageBitmap(decode(sharedPreferences.getString("imageFile1", null)!!))
            }else if (sharedPreferences.getString("imageFile2", null) != null){
                ivReceipt2.setImageBitmap(decode(sharedPreferences.getString("imageFile2", null)!!))
            }else if (sharedPreferences.getString("imageFile3", null) != null){
                ivReceipt3.setImageBitmap(decode(sharedPreferences.getString("imageFile3", null)!!))
            }

            if (sharedPreferences.getBoolean("image1", true)){
//                val fileUri1 = data?.data
                ivReceipt1.setImageURI(data?.data)
                ////
                val requestFile: RequestBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), ImagePicker.getFile(
                        data)!!)
                val body: MultipartBody.Part =
                    MultipartBody.Part.createFormData("image",
                        ImagePicker.getFile(data)!!.getName(),
                        requestFile)
                imagesArray?.add(body)
                ////
                editor.putString("imageFile1", encode(data?.data!!))
                editor.putBoolean("image1", false)
                editor.apply()
            }else if (sharedPreferences.getBoolean("image2", true)){
//                val fileUri2 = data?.data
                ivReceipt2.setImageURI(data?.data)
//                imagesArray?.add(ImagePicker.getFile(data)!!)
                ////
                val requestFile: RequestBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), ImagePicker.getFile(
                        data)!!)
                val body: MultipartBody.Part =
                    MultipartBody.Part.createFormData("image",
                        ImagePicker.getFile(data)!!.getName(),
                        requestFile)
                imagesArray?.add(body)
                ////
                editor.putString("imageFile2", encode(data?.data!!))
                editor.putBoolean("image2", false)
                editor.apply()
                ivReceipt1.setImageBitmap(decode(sharedPreferences.getString("imageFile1", null)!!))
            }else if (sharedPreferences.getBoolean("image3", true)){
//                val fileUri3 = data?.data
                ivReceipt3.setImageURI(data?.data)
                ////
                val requestFile: RequestBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), ImagePicker.getFile(
                        data)!!)
                val body: MultipartBody.Part =
                    MultipartBody.Part.createFormData("image",
                        ImagePicker.getFile(data)!!.getName(),
                        requestFile)
                imagesArray?.add(body)
                ////
                editor.putString("imageFile3", encode(data?.data!!))
                editor.putBoolean("image3", false)
                editor.apply()
                ivReceipt1.setImageBitmap(decode(sharedPreferences.getString("imageFile1", null)!!))
                ivReceipt2.setImageBitmap(decode(sharedPreferences.getString("imageFile2", null)!!))
            }else{
                ivReceipt1.setImageBitmap(decode(sharedPreferences.getString("imageFile1", null)!!))
                ivReceipt2.setImageBitmap(decode(sharedPreferences.getString("imageFile2", null)!!))
                ivReceipt3.setImageBitmap(decode(sharedPreferences.getString("imageFile3", null)!!))
                Toast.makeText(this, getString(R.string.notallowimage), Toast.LENGTH_SHORT).show()
            }
            Log.i("arrIm", imagesArray?.size.toString())
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

    fun encode(imageUri: Uri): String {
        val input = getContentResolver().openInputStream(imageUri)
        val image = BitmapFactory.decodeStream(input, null, null)

        // Encode image to base64 string
        val baos = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var imageBytes = baos.toByteArray()
        val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
        return imageString
    }

    fun decode(imageString: String): Bitmap? {

        // Decode base64 string to image
        val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        return decodedImage
//        imageview.setImageBitmap(decodedImage)
    }

    private fun onRequestSent(context: Context, rId: String) {

        val title = SpannableString(getString(R.string.thank))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.yrequest_number) + rId + getString(R.string.succes_done))
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

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.edit().clear().apply()
        Log.i("isCalled", "yes")
    }
}