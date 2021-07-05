package com.scorpion_a.studentapp.network

import android.content.Context
import com.scorpion_a.studentapp.model.requests.LoginRequests
import com.scorpion_a.studentapp.model.requests.ReqjectRequest
import com.scorpion_a.studentapp.model.requests.RequestRequests
import com.scorpion_a.studentapp.model.requests.UpdateUserRequests
import com.scorpion_a.studentapp.model.responses.*
import com.scorpion_a.studentapp.utils.SharedPreferenceClass
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

 @GET("user")
 fun getUserDataDo(@Header("Authorization") Authorization: String,
 ):Call<UserDataResponce>

    @GET("requests")
    @Headers("Accept: application/json", "Content-Type: application/json"/*,"Locale: ar"*/)
    fun getRequestsData(
    ):Call<MyRequestsResponse>

    @GET("request-types")
    @Headers("Accept: application/json", "Content-Type: application/json"/*,"Locale: ar"*/)
    fun getRequestsTypesData(
    ):Call<RequestsResponse>

    @Multipart
    @POST("requests")
    @Headers("Accept: application/json", "Content-Type: application/json")
//    fun submitRequest(@Part request: RequestRequests): Call<SubmitRequestResponse>
    fun submitRequest(@Header ("Authorization") Authorization: String, @Part("request_type_id") request_type_id: RequestBody, @Part("count") count: RequestBody,
                      @Part("receipt[]")receipt: ArrayList<MultipartBody.Part>): Call<SubmitRequestResponse>

 @POST("requests-an")
 @FormUrlEncoded
 fun submitRequestBase(@Header ("Authorization") Authorization: String,@Field("request_type_id") request_type_id:String,
                       @Field("count") count:String,
  @Field("image_1") image_1:String,
                       @Field("image_2") image_2:String,
                       @Field("image_3") image_3:String): Call<SubmitRequestResponse>


    @PUT("user")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun updateUser(@Body request: UpdateUserRequests): Call<UserDataResponce>

    @GET("articles")
    @Headers("Accept: application/json", "Content-Type: application/json"/*,"Locale: ar"*/)
    fun getArticlesData(
    ):Call<ArticlesResponse>

    @GET("articles/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json"/*,"Locale: ar"*/)
    fun getArticleDetails(@Path("id") id:String
    ):Call<ArticleDetailsResponse>

 @PUT("requests/{id}/approve")
 @Headers("Accept: application/json", "Content-Type: application/json")
 fun approveReq(@Path("id") id:String,@Body _method: String): Call<ActionsResponce>


 @PUT("requests/{id}/reject")
 @Headers("Accept: application/json", "Content-Type: application/json")
 fun rejectReq(@Path("id") id:String,@Body reject: ReqjectRequest): Call<ActionsResponce>

 @PUT("requests/{id}/done")
 @Headers("Accept: application/json", "Content-Type: application/json")
 fun doneReq(@Path("id") id:String,@Body _method: String): Call<ActionsResponce>

 @POST("user")
 @FormUrlEncoded
 @Headers("Accept: application/json", "Content-Type: application/json")
 fun updateProfilePic(
  @Header("Authorization") Authorization: String,
  @Field("image") image:String
//  @Part image: MultipartBody.Part
//  @Part("_method") _method: RequestBody
 ): Call<UserDataResponce>

}