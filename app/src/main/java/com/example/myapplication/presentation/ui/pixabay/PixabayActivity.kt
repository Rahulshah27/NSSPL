package com.example.myapplication.presentation.ui.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPixabayBinding

class PixabayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPixabayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pixabay)

    }

}