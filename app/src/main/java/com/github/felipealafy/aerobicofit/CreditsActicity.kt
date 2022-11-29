package com.github.felipealafy.aerobicofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import com.github.felipealafy.aerobicofit.databinding.ActivityCreditsActicityBinding
import com.github.felipealafy.aerobicofit.databinding.ActivitySettingsBinding

class CreditsActicity : AppCompatActivity() {
    lateinit var binding: ActivityCreditsActicityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreditsActicityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.links.movementMethod = LinkMovementMethod.getInstance()
    }
}