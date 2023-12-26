package com.example.rapid_api_task

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitObject {
    val baseurl = "https://odds.p.rapidapi.com/"
    val retrofit = Retrofit.Builder().baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofieApi = retrofit.create(RetrofiApi::class.java)
}