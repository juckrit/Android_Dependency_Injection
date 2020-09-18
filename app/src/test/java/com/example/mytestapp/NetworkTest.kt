package com.example.mytestapp

import com.example.mytestapp.Activity.Api.ContentApi
import com.example.mytestapp.Activity.di.networkModule
import junit.framework.Assert
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import retrofit2.Retrofit


class NetworkTest : KoinTest {
    val api: ContentApi by inject()
    val retrofit: Retrofit by inject()
    val okHttpClient: OkHttpClient by inject()
    val baseUrl: String by lazy { get(named("BASE_ORIGINAL_CONTENT_URL")) as String }


    @Before
    fun setup() {
        startKoin {
            modules(listOf(networkModule))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Test Retrofit Created`() {
        assertNotNull(retrofit)
        assert(baseUrl == "https://sistacafe.com/api/v2/categories/5/")
    }

    @Test
    fun `Test API Created`() {
        assertNotNull(api)
    }

    @Test
    fun `Test OKHttp`() {
        assertNotNull(okHttpClient)
        Assert.assertTrue(okHttpClient.connectTimeoutMillis == 30000)
        Assert.assertTrue(okHttpClient.writeTimeoutMillis == 30000)
        Assert.assertTrue(okHttpClient.readTimeoutMillis == 30000)
        Assert.assertTrue(okHttpClient.interceptors.size == 1)
    }
}