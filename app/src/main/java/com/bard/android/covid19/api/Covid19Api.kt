package com.bard.android.covid19.api

import retrofit2.Call
import retrofit2.http.GET

interface Covid19Api{
    @GET("/summary")
    fun fetchCovidData(): Call<Covid19Response>
}