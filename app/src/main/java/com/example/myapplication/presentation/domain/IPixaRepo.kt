package com.example.myapplication.presentation.domain

import com.example.myapplication.data.model.ResultOf
import com.example.myapplication.presentation.model.Pixas

interface IPixaRepo {
    suspend fun getAllPixaImageList(): ResultOf<List<Pixas>>
}