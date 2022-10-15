package com.tkpd.hackathon17.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tkpd.hackathon17.databinding.ActivityListProductBinding
import com.tkpd.hackathon17.viewmodel.ViewModelFactory

class ListProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListProductBinding
    private lateinit var viewModel: ListProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ListProductViewModel::class.java]

    }
}