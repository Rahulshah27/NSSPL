package com.example.myapplication.presentation.ui.show

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.model.ResultOf
import com.example.myapplication.databinding.ActivityShowBinding
import com.example.myapplication.network.adapter.PostAdapter
import com.example.myapplication.presentation.viewmodel.PixabayVM
import com.example.myapplication.presentation.viewmodel.PixabayVMFactory
import com.example.myapplication.util.showToast

class ShowActivity : AppCompatActivity() {

    private val pixabayVM by viewModels<PixabayVM> { PixabayVMFactory() }

    private lateinit var binding: ActivityShowBinding
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { PostAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show)

        doFetchApi()
        subscribeToObservers()
        binding.rvPostImage.adapter = adapter
        binding.rvPostImage.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            setHasFixedSize(true)
        }
    }

    private fun subscribeToObservers() {
        pixabayVM.obPixaList.observe(this) {
            when (it) {
                is ResultOf.Progress -> showToast(if (it.loading) "Loading.. " else "Loaded")
                is ResultOf.Success -> {
                    adapter.items = it.value
                }
                is ResultOf.Empty -> showToast("No data available!")
                is ResultOf.Failure -> {
                    showToast(it.message!!)
                }

                else -> {showToast("Unexpected Error!")}
            }
        }
    }

    private fun doFetchApi() {
        pixabayVM.getPixasList()
    }
}