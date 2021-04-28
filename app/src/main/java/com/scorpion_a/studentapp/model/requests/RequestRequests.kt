package com.scorpion_a.studentapp.model.requests

import com.google.gson.annotations.SerializedName
import java.io.File

class RequestRequests (
    var request_type_id: String,
    var count: Int,
    @SerializedName("receipt[]")
    var receipt: ArrayList<File>
)