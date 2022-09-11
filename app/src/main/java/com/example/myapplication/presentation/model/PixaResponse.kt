package com.example.myapplication.presentation.model
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class PixaResponse (
    @Json(name = "message")
    val message:List<ItemDatas>?=null
)

data class ItemDatas(
    @SerializedName("message")
    val message: Any?,
)

data class PixaImage(
    @Json(name = "message")
    val message: String?=""
)

data class Pixas(
    val tags : String?,
    val imageUrl: String?
)