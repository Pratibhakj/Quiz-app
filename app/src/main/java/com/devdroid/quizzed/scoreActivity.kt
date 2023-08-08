package com.devdroid.quizzed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.devdroid.quizzed.databinding.ActivityScoreBinding

class scoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.score.setText("Congrats!!Your score is ${intent.getIntExtra("Score",0)}")
    }
}