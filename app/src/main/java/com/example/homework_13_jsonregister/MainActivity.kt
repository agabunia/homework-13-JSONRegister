package com.example.homework_13_jsonregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework_13_jsonregister.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}