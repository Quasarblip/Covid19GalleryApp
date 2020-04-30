package com.bard.android.covid19

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bard.android.covid19.api.CountryResponse
import com.bard.android.covid19.api.Covid19Api
import com.bard.android.covid19.api.Covid19Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "Covid19Fetcher"

class Covid19Fetcher{

    private val covid19Api: Covid19Api

    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        covid19Api = retrofit.create(Covid19Api::class.java)
    }

    fun fetchCovidData(): LiveData<List<GalleryItem>>{
        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        val covid19Request: Call<Covid19Response> = covid19Api.fetchCovidData()

        covid19Request.enqueue(object: Callback<Covid19Response>{

            override fun onFailure(call: Call<Covid19Response>, t: Throwable) {
                Log.e(TAG, "Failed to fetch data", t)
            }

            override fun onResponse(call: Call<Covid19Response>, response: Response<Covid19Response>) {
                Log.d(TAG, "Response Received ")
                val covid19Response: Covid19Response? = response.body()
                //need to ad GloabalResponse in api
                //var WorldWide: GalleryItem = covid19Response.Global
                var galleryItems: List<GalleryItem> = covid19Response?.Countries
                    ?: mutableListOf()

                val countriesToKeep = listOf<String>()
                //useful filtering to cut down on 250+ records?
                galleryItems = galleryItems.filterNot{
                    //try to filter out any country that has no country code
                    it.CountryCode.isBlank()
                }

                responseLiveData.value = galleryItems
            }
        })
        return responseLiveData
    }

}