package com.feritkeskin.pixabayjetpack.model

import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
)