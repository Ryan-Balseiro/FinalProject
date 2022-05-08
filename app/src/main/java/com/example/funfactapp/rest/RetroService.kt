package com.example.funfactapp.rest


import androidx.lifecycle.ViewModelProvider
import com.example.funfactapp.model.RecyclerList
import com.example.funfactapp.viewmodel.FactViewModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {


    @GET("")
    suspend fun getDataFromApi(@Query("num") query: String): RecyclerList
}