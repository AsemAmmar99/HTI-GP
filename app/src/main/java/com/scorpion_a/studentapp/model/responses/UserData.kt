package com.scorpion_a.studentapp.model.responses

data class UserData (
    var user_id:String,
    var name:String,
    var email:String,
    var image_path:String,
    var gpa:String,
    var number_of_units:String,
    var account_type:String,
    var department:String
)