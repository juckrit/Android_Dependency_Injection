package com.example.mytestapp.Activity.di

import com.example.mytestapp.Activity.Api.ContentApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single(named("BASE_ORIGINAL_CONTENT_URL")){
        "https://sistacafe.com/api/v2/categories/5/"
    }

    single {
        GsonConverterFactory.create(GsonBuilder().create())
    }
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }
    single {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

//        if (BuildConfig.DEBUG) {
//            client.addInterceptor(get<HttpLoggingInterceptor>())
//        }
        client.addInterceptor(get<HttpLoggingInterceptor>())

        client.build()
    }

    single {
        Retrofit.Builder().baseUrl(get<String>(named("BASE_ORIGINAL_CONTENT_URL")))
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }

    single {
        get<Retrofit>().create(ContentApi::class.java)
    }
}