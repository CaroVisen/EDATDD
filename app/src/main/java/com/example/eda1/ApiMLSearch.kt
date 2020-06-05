package com.example.eda1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMLSearch {
    @GET("search?q=")
    fun getAllArticles(@Query("q") query: String): Call<ListaArticulos>
}