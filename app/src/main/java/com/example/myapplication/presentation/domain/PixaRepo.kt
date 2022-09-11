package com.example.myapplication.presentation.domain

import com.example.myapplication.data.model.ResultOf
import com.example.myapplication.data.remote.PixabayService
import com.example.myapplication.presentation.model.Pixas

class PixaRepo(private val pixaApi: PixabayService): IPixaRepo {

    override suspend fun getAllPixaImageList(): ResultOf<List<Pixas>> {
        val list = mutableListOf<Pixas>()

        val pixaImageList = pixaApi.getPixaList().message.keys.toList()
        pixaImageList.forEach {
            val pixaImage = pixaApi.getImageByUrl(it).message
            list.add(Pixas(it,pixaImage))
        }
        return ResultOf.Success(list)
    }
}