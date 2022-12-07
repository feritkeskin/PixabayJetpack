package com.feritkeskin.pixabayjetpack.service

import com.feritkeskin.pixabayjetpack.api.PixabayAPI
import com.feritkeskin.pixabayjetpack.model.Model
import com.feritkeskin.pixabayjetpack.util.Util.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PixabayAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PixabayAPI::class.java)

    fun getData(searchImage: String): Single<Model> {
        return api.imageSearch(search = searchImage)
    }

}