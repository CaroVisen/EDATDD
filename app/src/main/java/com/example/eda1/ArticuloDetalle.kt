package com.example.eda1

import com.google.gson.annotations.SerializedName

data class ArticuloDetalle (@SerializedName("id")var id: String,
                            @SerializedName("plain_text")var plain_text: String) {
}