package com.scorpion_a.studentapp.network

import android.content.Context
import com.scorpion_a.studentapp.model.requests.LoginRequests
import com.scorpion_a.studentapp.model.responses.LoginResponse
import com.scorpion_a.studentapp.model.responses.UserDataResponce
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import retrofit2.Call
import retrofit2.http.*

interface Service {
    var context: Context;

    companion object {
    const val BaseUrl = "https://app.jabbarproject.com/"

   }
    fun getContext(mContext:Context){
        context = mContext
    }
    @POST("login")
    @Headers("Content-Type: application/json")
    fun getLoginData(@Body login: LoginRequests): Call<LoginResponse>

    @GET("user")
    fun getUserData(
//        @Header("Authorization") auth:String
    ):Call<UserDataResponce>
//    @Field("token") token:String


}