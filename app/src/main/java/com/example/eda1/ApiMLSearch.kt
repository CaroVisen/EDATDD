package com.example.eda1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMLSearch {
    @GET("sites/MLA/search")
    fun getAllArticles(@Query("q") query: String): Call<ListaArticulos>

    @GET("items/{itemId}")
    fun getOneArticle(@Path("itemId") id: String): Call<Articulo>

    @GET("items/{itemId}/description")
    fun getArticleDetalle(@Path("itemId") id: String): Call<ArticuloDetalle>
}

