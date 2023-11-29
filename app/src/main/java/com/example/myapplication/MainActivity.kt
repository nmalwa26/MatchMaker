package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val easyMode = findViewById<Button>(R.id.Easy_button)
        easyMode.setOnClickListener{
            val Intent = Intent(this,EasyMode::class.java)
            startActivity(Intent)
        }

        val mediumMode = findViewById<Button>(R.id.Medium_button)
        mediumMode.setOnClickListener{
            val Intent = Intent(this,MediumMode::class.java)
            startActivity(Intent)
        }
    }
}