package com.devdroid.quizzed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            if (Firebase.auth.currentUser!=null){
                val iHome = Intent(this@MainActivity, QuizActivity::class.java)
                startActivity(iHome)
                finish()
            }
             else{
                val iHome = Intent(this@MainActivity, LoginActivity::class.java)
                intent.putExtra("MODE","SIGNUP")
                startActivity(iHome)
                finish()
             }

        }, 4000)
    }
}