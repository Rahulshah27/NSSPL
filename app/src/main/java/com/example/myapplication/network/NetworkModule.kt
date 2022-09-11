package com.example.myapplication.network

import android.content.Context
import com.example.myapplication.BuildConfig
import com.example.myapplication.common.BaseApp
import com.example.myapplication.data.remote.PixabayService
import com.example.myapplication.network.adapter.NullToEmptyStringAdapter
import com.example.myapplication.network.adapter.StringToBooleanAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    fun makeApiService(): PixabayService = Retrofit.Builder()
        .baseUrl("https://pixabay.com/api/")
        .client(okHttpClient(BaseApp.getAppContext()).build())
        .addConverterFactory(MoshiConverterFactory.create(moshiFactory()))
        .build()
        .create(PixabayService::class.java)


    private fun moshiFactory(): Moshi {
        return Moshi.Builder()
            .add(StringToBooleanAdapter())
            .add(NullToEmptyStringAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    private fun okHttpClient(applicationContext: BaseApp) =
        okHttpBuilder(applicationContext)

    private fun okHttpBuilder(context: Context) = OkHttpClient.Builder()
        .addInterceptor(makeLoggingInterceptor())
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)


    private fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level =if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

}