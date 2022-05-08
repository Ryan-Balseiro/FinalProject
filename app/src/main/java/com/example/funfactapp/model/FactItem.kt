package com.example.funfactapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FactItem(
    @Json(name = "result")
    val result: ArrayList<String>?
)