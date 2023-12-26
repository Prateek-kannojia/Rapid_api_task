package com.example.rapid_api_task

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofiApi {
    @Headers(
        "X-RapidAPI-Key: 06bf1b8402msh46b67f184ae6d51p1d3c14jsnfd916eb10ba2",
        "X-RapidAPI-Host: odds.p.rapidapi.com"
    )
    @GET("v4/sports")
    fun getSportsCategories():Call<List<SportsCategory>>
}