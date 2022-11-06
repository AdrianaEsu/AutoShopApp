package com.example.autoshopsplash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.autoshopsplash.databinding.ActivityRestablecerBinding

class RestablecerActivity: AppCompatActivity() {
    lateinit var binding: ActivityRestablecerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestablecerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}