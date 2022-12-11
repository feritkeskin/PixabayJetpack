package com.feritkeskin.pixabayjetpack.api

import com.feritkeskin.pixabayjetpack.model.Model
import com.feritkeskin.pixabayjetpack.util.Util.API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    //https://pixabay.com/api/?key=30144454-5375a98f7807360676361c029&qMona

    @GET("/api/")
    fun imageSearch(
        @Query("q") search: String,
        @Query("key") key: String = API_KEY
    ): Single<Model>

}