package com.example.eda1

import com.google.gson.annotations.SerializedName

data class ListaArticulos(@SerializedName("results") var results: List<Articulo>) {
}