package com.example.appcenter_project_1.model

import android.widget.EditText
import com.example.appcenter_project_1.R
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface PapagoService {

    @FormUrlEncoded
    @POST("detectLangs")
    fun requestTranslation(@Header("X-Naver-Client-Id")clientID: String = Configs.clientID,
                           @Header("X-Naver-Client-Secret")apiKey: String = Configs.apiKey,
                           @Field("query")query: String?) : Call<PapagoEntity>
}