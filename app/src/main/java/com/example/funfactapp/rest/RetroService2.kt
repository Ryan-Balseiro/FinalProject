package com.example.funfactapp.rest

import com.example.funfactapp.model.DataItem
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService2 {

    @GET("?num=1")
    fun getDataFromApi2()
            : Call<List<String>>

    @GET(END_POINT)
    fun getDataFromApi4()
            : Call<DataItem>


    companion object{
//        const val BASE_URL = "https://api.aakhilv.me/fun/facts/"
        const val BASE_URL = "https://uselessfacts.jsph.pl/"
        const val END_POINT = "random.json?language=en"

        fun getRetroInstance2(): RetroService2 {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetroService2::class.java)
        }
    }
}