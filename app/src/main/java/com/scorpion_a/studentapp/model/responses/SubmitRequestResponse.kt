package com.scorpion_a.studentapp.model.responses

import com.scorpion_a.studentapp.model.ViewRequestsListData

data class SubmitRequestResponse (
//    var data:List<ViewRequestsListData>
var message:String,
var data:ReId
)
data class ReId(
    var id:String
)

//"message": "Request created",
//"data": {
//    "id": 97
//}