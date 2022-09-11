package com.example.myapplication.presentation.model

import com.google.gson.annotations.SerializedName

data class ItemData<T>(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: T
)
