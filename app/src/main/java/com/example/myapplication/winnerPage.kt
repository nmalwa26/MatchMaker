package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class winnerPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.winner_page)

        //button that leads back to home page
        val btn = findViewById<Button>(R.id.hp)
        btn.setOnClickListener {
            val Intent1 = Intent(this@winnerPage,MainActivity::class.java)
            startActivity(Intent1)
        }
    }
}