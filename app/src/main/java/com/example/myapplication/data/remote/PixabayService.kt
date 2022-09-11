package com.example.myapplication.data.remote

import com.example.myapplication.presentation.model.ItemData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PixabayService {
    @GET("?key=29827292-6ab77708174016e2849717bb5&q=yellow+{searchName}&image_type=photo&pretty=true")
    fun getImageByUrlAsync(@Path("searchName") searchName: String): Call<ItemData<String>>

    suspend fun getPixaList(): ItemData<Map<String, List<String>>>

    @GET("?key=29827292-6ab77708174016e2849717bb5&q=yellow+{searchName}&image_type=photo&pretty=true")
    suspend fun getImageByUrl(@Path("searchName") searchName: String): ItemData<String>
}