package com.example.mytestapp.Activity.Api

import com.example.mytestapp.Activity.Model.ApiResultModel
import com.example.mytestapp.Activity.Util.BASE_ORIGINAL_CONTENT_URL
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ContentApi {

    @GET("summaries")
    fun getOriginalContentSummariesModel(@Query("last_summary_id") last_summary_id: Int = 0): Call<ApiResultModel>


    companion object {
        val contentApi: ContentApi by lazy {
            Retrofit.Builder().baseUrl(BASE_ORIGINAL_CONTENT_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(ContentApi::class.java)
        }
    }
}