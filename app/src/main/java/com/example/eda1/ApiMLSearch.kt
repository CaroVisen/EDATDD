package com.example.eda1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMLSearch {
    @GET("sites/MLA/search")
    fun getAllArticles(@Query("q") query: String): Call<ListaArticulos>

    @GET("items/{itemId}")
    fun getArticle(@Path("itemId") id: String): Call<Articulo>
}

