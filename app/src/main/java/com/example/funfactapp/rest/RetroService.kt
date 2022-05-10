package com.example.funfactapp.rest


import com.google.gson.JsonArray
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {


    @GET("")
    suspend fun getDataFromApi(
        @Query("num") query: String)
    : JsonArray

//    @GET("")
//    suspend fun getDataFromApi(
//        @Query("num") query: String)
//            : Call<List<String>>
}