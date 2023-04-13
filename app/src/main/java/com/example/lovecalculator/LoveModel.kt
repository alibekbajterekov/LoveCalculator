package com.example.lovecalculator

import com.google.gson.annotations.SerializedName

data class LoveModel(
    @SerializedName("fname")
    var firstNameame:String,
    @SerializedName("sname")
    var secondName:String,
    var percentage:String,
    var result:String
)