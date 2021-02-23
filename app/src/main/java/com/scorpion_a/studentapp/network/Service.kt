package com.scorpion_a.studentapp.network

import android.content.Context
import com.scorpion_a.studentapp.model.requests.LoginRequests
import com.scorpion_a.studentapp.model.requests.RequestRequests
import com.scorpion_a.studentapp.model.responses.*
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
    ):Call<UserDataResponce>

    @GET("requests")
    @Headers("Accept: application/json", "Content-Type: application/json"/*,"Locale: ar"*/)
    fun getRequestsData(
    ):Call<MyRequestsResponse>

    @GET("request-types")
    @Headers("Accept: application/json", "Content-Type: application/json"/*,"Locale: ar"*/)
    fun getRequestsTypesData(
    ):Call<RequestsResponse>

    @POST("requests")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun submitRequest(@Body request: RequestRequests): Call<SubmitRequestResponse>

}