package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.ResultOf
import com.example.myapplication.presentation.domain.IPixaRepo
import com.example.myapplication.presentation.model.Pixas
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PixabayVM(val pixaRepo: IPixaRepo) : ViewModel(), CoroutineScope {
    private val parentJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    val obPixaList = MutableLiveData<ResultOf<List<Pixas>>?>()

    fun performDelayAction(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                delay(3000)

            }
        }
    }

    fun getPixasList(){
        viewModelScope.launch {
            runCatching {
                pixaRepo.getAllPixaImageList()
            }.onSuccess {
                obPixaList.value = it
            }
        }
    }

}