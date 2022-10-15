package com.tkpd.hackathon17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tkpd.hackathon17.databinding.ActivityMainBinding
import com.tkpd.hackathon17.ui.input.InputProductActivity
import com.tkpd.hackathon17.ui.list.ListProductActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, InputProductActivity::class.java))
        }
        binding.btnList.setOnClickListener {
            startActivity(Intent(this@MainActivity, ListProductActivity::class.java))
        }
    }
}