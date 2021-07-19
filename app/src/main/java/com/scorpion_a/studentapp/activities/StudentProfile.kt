package com.scorpion_a.studentapp.activities

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.dhaval2404.imagepicker.ImagePicker
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.network.Service
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import com.scorpion_a.studentapp.utils.Theme
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_confirm_request.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_student_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File

class StudentProfile : BaseActivity() {
    lateinit var toolbar: Toolbar
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    var file: File?=null
    var fileUri:Uri?=null
    var imagepath:String?=null
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.profile_page)
        Connection.isNetworkAvailable(this)

        sharedPreferences =
            this.getSharedPreferences("images", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        uploadimage.visibility=View.GONE
//        if(sharedPreferences.getString("imagepath", null)?:""==null){
//            ivProfilePict.setImageResource(R.drawable.avatar)
//
//        }else{
//        ivProfilePict.setImageBitmap(decode(sharedPreferences.getString("imagepath", null)!!))}
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

        mSwipeRefreshLayout= findViewById(R.id.swipe_refresh_layout)
        mSwipeRefreshLayout!!.setOnRefreshListener {
            call.clone().enqueue(object : Callback<UserDataResponce> {
                override fun onResponse(
                    call: Call<UserDataResponce>,
                    response: Response<UserDataResponce>
                ) {

                    if (response.isSuccessful()){
                        mSwipeRefreshLayout!!.isRefreshing = false
//                    tvDepartment.text="Department: " + response.body().data?.department
                        tvStudentDepartment.text=response.body().data?.department
                        tvGPAResultProfile.text=response.body().data?.gpa
                        tvUnitsValueProfile.text=response.body().data?.number_of_units
                        tvPhoneValue.text=response.body().data?.phone
                        tvStudentNameArabic.text=response.body().data?.name?.ar
                        tvStudentNameEngProfile.text=response.body().data?.name?.en
                        if(sharedPreferences.getString("imagepath", null)?:""==null){
//                            ivProfilePict.setImageResource(R.drawable.avatar)
                            Picasso.with(baseContext).load("https://app.jabbarproject.com"+response.body().data?.image_path).placeholder(R.drawable.avatar).into(ivProfilePict)


                        }else{
                            ivProfilePict.setImageBitmap(decode(sharedPreferences.getString("imagepath", null)!!))}
                        tvEmailValueProfile.text=response.body().data?.email
                        tvIDValueProfile.text=response.body().data?.user_id
                        progressBarProfile.visibility = View.GONE
                        clProfilePage.visibility = View.VISIBLE
                    }else{
                        mSwipeRefreshLayout!!.isRefreshing = false
                        progressBarProfile.visibility = View.GONE
                        clProfilePage.visibility = View.VISIBLE
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
        progressBarProfile.visibility = View.VISIBLE
        clProfilePage.visibility = View.INVISIBLE
        Log.i("ccall",call.request().toString())
        call.clone().enqueue(object : Callback<UserDataResponce> {
            override fun onResponse(
                call: Call<UserDataResponce>,
                response: Response<UserDataResponce>
            ) {

                if (response.isSuccessful()){
//                    tvDepartment.text="Department: " + response.body().data?.department
                    tvStudentDepartment.text=response.body().data?.department
                    tvGPAResultProfile.text=response.body().data?.gpa
                    tvUnitsValueProfile.text=response.body().data?.number_of_units
                    tvPhoneValue.text=response.body().data?.phone
                    tvStudentNameArabic.text=response.body().data?.name?.ar
                    tvStudentNameEngProfile.text=response.body().data?.name?.en
                    if(sharedPreferences.getString("imagepath", null)?:""==null){
//                        ivProfilePict.setImageResource(R.drawable.avatar)
                            Picasso.with(baseContext).load("https://app.jabbarproject.com"+response.body().data?.image_path).placeholder(R.drawable.avatar).into(ivProfilePict)


                    }else{
                        if (sharedPreferences.getString("imagepath",
                                null)!=null) {
                            ivProfilePict.setImageBitmap(decode(sharedPreferences.getString("imagepath",
                                null)!!))
                        }
                    }
                    tvEmailValueProfile.text=response.body().data?.email
                    tvIDValueProfile.text=response.body().data?.user_id
                    progressBarProfile.visibility = View.GONE
                    clProfilePage.visibility = View.VISIBLE
                }else{
                    progressBarProfile.visibility = View.GONE
                    clProfilePage.visibility = View.VISIBLE
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

        ivEdit.setOnClickListener {
            val intent = Intent(this, StudentEditProfile::class.java)
            intent.putExtra("ar", tvStudentNameArabic.text.toString())
            intent.putExtra("en", tvStudentNameEngProfile.text.toString())
            intent.putExtra("email", tvEmailValueProfile.text.toString())
            intent.putExtra("phone", tvPhoneValue.text.toString())
            startActivity(intent)
        }

        ivProfilePict.setOnClickListener{
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        uploadimage.setOnClickListener {
            val logging = HttpLoggingInterceptor()
// set your desired log level
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(logging) //

            val filePart = MultipartBody.Part.createFormData(
                "image",
                file!!.name,
                RequestBody.create(MediaType.parse("image/*"), file)
            )

//            val filePart: RequestBody = RequestBody.create(
//                MediaType.parse(this.contentResolver.getType(fileUri!!)!!),
//                file
//            )

//            var body = MultipartBody.Part.createFormData("file", file?.name, filePart)
            var requestFile: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            var body: MultipartBody.Part =
                MultipartBody.Part.createFormData("image",
                    file?.name,
                    requestFile)

            var methods=RequestBody.create(MediaType.parse("application/json"), "PATCH")

            
            val retrofit1 = Retrofit.Builder()
                .baseUrl(Service.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(OkHttpClient.Builder().addInterceptor { chain ->
//                    val request = chain.request().newBuilder().addHeader("Authorization", ).build()
//                    chain.proceed(request)
//                }.build())
                .client(httpClient.build())

                .build()

            val client1 = retrofit1.create(Service::class.java)


//            Log.i("callll",imagepath)
            var call1 = client1.updateProfilePic("Bearer ${SharedPreferenceClass.loadString(this,"TOKEN")}",
                sharedPreferences.getString("imagepath", null)!!
            )


            call1.clone().enqueue(object : Callback<UserDataResponce> {
                override fun onResponse(
                    call: Call<UserDataResponce>,
                    response: Response<UserDataResponce>
                ) {

                    if (response.isSuccessful()){
//                    tvDepartment.text="Department: " + response.body().data?.department

                    }else{

                        Toast.makeText(baseContext, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
//                    Toast.makeText(this@CheckoutActivity, jObjError.toString(), Toast.LENGTH_LONG).show()
                            Log.i("erroooooooooooooor", jObjError.toString())
                        } catch (e: Exception) {
//                    Toast.makeText(this@CheckoutActivity, e.message, Toast.LENGTH_LONG).show()
                        }
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
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
//                    Toast.makeText(this@CheckoutActivity, jObjError.toString(), Toast.LENGTH_LONG).show()
                        Log.i("erroooooooooooooor", jObjError.toString())
                    } catch (e: Exception) {
//                    Toast.makeText(this@CheckoutActivity, e.message, Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<UserDataResponce>?, t: Throwable?) {
                    Log.i("test", t.toString())
                }
            })

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


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data?.getData()!=null) {
            //Image Uri will not be null for RESULT_OK
            fileUri = data?.data
            ivProfilePict.setImageURI(fileUri)

            //You can get File object from intent
            file = ImagePicker.getFile(data)!!


            //You can also get File Path from intent
            val filePath:String = ImagePicker.getFilePath(data)!!

            imagepath=encode(data.data!!)
            editor.putString("imagepath", encode(data.data!!))
            editor.apply()

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }else  if (resultCode !== Activity.RESULT_CANCELED) {
//            if (requestCode === CAMERA_REQUEST) {
//                val photo: Bitmap? = data!!.extras!!["data"] as Bitmap?
//                imageView.setImageBitmap(photo)
//
//        }
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}