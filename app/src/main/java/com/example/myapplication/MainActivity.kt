package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //adds background music to the app
        var mMediaPlayer: MediaPlayer? = null

        //creates button that can start back the music
        val soundplayButton = findViewById<ImageButton>(R.id.soundPlay)
        soundplayButton.setOnClickListener{
            if (mMediaPlayer == null) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.song)
                mMediaPlayer!!.isLooping = true
                mMediaPlayer!!.start()
            }
        }

        //creates a button that can stop music
        val soundpauseButton = findViewById<ImageButton>(R.id.soundPause)
        soundpauseButton.setOnClickListener{
            mMediaPlayer != null
            if (mMediaPlayer != null) {
                mMediaPlayer!!.stop()
                mMediaPlayer!!.release()
                mMediaPlayer = null
            }
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

        val hardMode = findViewById<Button>(R.id.Hard_button)
        hardMode.setOnClickListener{
            val Intent = Intent(this,HardMode::class.java)
            startActivity(Intent)
        }
    }
}