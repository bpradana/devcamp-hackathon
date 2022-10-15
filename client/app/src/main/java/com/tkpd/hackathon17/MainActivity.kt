package com.tkpd.hackathon17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.tkpd.hackathon17.databinding.ActivityMainBinding
import com.tkpd.hackathon17.ui.input.InputProductActivity
import com.tkpd.hackathon17.ui.list.ListProductActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@MainActivity, ListProductActivity::class.java))
        }, 1500)
    }
}