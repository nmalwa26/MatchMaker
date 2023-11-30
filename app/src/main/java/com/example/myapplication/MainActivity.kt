package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import edu.stanford,R.drawable.*

private const val TAG = "MainAcitvity"
class MainActivity : AppCompatActivity() {
    private lateinit var buttons: List<ImageButtons>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = mutableListOf<>()

        buttons = listof(imageButton11, imageButton25, imageButton26, imageButton27, imageButton28, imageButton29
        imageButton30, imageButton31)

        for (button in buttons){
            buttons.setClickonListener {
                Log.i(TAG, "button clicked!")
        }


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