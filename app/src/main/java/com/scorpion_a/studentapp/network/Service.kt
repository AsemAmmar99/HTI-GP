package com.scorpion_a.studentapp.network

import com.scorpion_a.studentapp.model.requests.LoginRequests
import com.scorpion_a.studentapp.model.responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {
   companion object {
    const val BaseUrl = "https://app.jabbarproject.com/"
   }
    @POST("login")
    @Headers("Content-Type: application/json")
    fun getLoginData(@Body login: LoginRequests): Call<LoginResponse>
}