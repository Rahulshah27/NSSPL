package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.network.NetworkModule
import com.example.myapplication.presentation.domain.IPixaRepo
import com.example.myapplication.presentation.domain.PixaRepo

class PixabayVMFactory: ViewModelProvider.Factory {

    init {
        getInstance()
    }
    companion object{
        @Volatile
        private var INSTANCE:IPixaRepo?=null
        fun getInstance() =
            INSTANCE ?: synchronized(PixabayVMFactory::class.java){
                INSTANCE?:PixaRepo(
                    NetworkModule.makeApiService()
                ).also { INSTANCE = it }
            }
        fun destroyInstance(){
            INSTANCE = null
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IPixaRepo::class.java).newInstance(INSTANCE)
    }
}