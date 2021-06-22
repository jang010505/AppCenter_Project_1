package com.example.appcenter_project_1.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PapagoServiceCreator {
    val BASE_URL = "https://openapi.naver.com/v1/papago/"

    fun create() : PapagoService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PapagoService::class.java)
    }
}