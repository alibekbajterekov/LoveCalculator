package com.example.lovecalculator.remote

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    var retofit = Builder().baseUrl("https://love-calculator.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create(Gson())).build()

    var api = retofit.create(LoveApi::class.java)
}