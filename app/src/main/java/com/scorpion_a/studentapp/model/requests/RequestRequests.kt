package com.scorpion_a.studentapp.model.requests

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class RequestRequests (
    var request_type_id: RequestBody,
    var count: RequestBody,
    @SerializedName("receipt[]")
    var receipt: ArrayList<MultipartBody.Part>
)