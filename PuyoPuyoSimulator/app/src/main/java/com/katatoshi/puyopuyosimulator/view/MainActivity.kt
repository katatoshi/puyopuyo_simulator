package com.katatoshi.puyopuyosimulator.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.katatoshi.puyopuyosimulator.R
import com.katatoshi.puyopuyosimulator.databinding.ActivityMainBinding
import com.katatoshi.puyopuyosimulator.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        MainViewModel()
    }

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel =  viewModel
    }

    override fun onResume() {
        super.onResume()

        viewModel.onResume()
    }
}
