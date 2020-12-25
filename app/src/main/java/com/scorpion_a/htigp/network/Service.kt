package com.scorpion_a.htigp.network

import com.scorpion_a.htigp.model.requests.LoginRequests
import com.scorpion_a.htigp.model.responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {
   companion object {
    const val BaseUrl = "http://api.jabbarproject.com/api/auth/"
   }
    @POST("login")
    @Headers("Content-Type: application/json")

    fun getLoginData(@Body login: LoginRequests): Call<LoginResponse>
}