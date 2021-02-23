package com.scorpion_a.studentapp.model.responses

import com.scorpion_a.studentapp.model.ViewRequestsListData

data class SubmitRequestResponse (
    var data: ViewRequestsListData, var message: String
)