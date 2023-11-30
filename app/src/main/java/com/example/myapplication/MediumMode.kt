package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R.drawable.baseline_bedtime_24
import com.example.myapplication.R.drawable.baseline_castle_24
import com.example.myapplication.R.drawable.baseline_cloud_24
import com.example.myapplication.R.drawable.baseline_electric_car_24
import com.example.myapplication.R.drawable.baseline_favorite_24
import com.example.myapplication.R.drawable.baseline_star_24

private const val TAG="MediumMode"
private lateinit var buttons: List<ImageButton>
private lateinit var cards: List<MemoryCard>
private var indexOfSingleSelectedCard: Int? = null
class MediumMode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_mode)
        var timeText2 = findViewById<TextView>(R.id.TimerText2)

        val images = mutableListOf( baseline_bedtime_24, baseline_cloud_24, baseline_favorite_24, baseline_star_24,
            baseline_electric_car_24, baseline_castle_24)
        images.addAll(images)
        images.shuffle()

        val imageButton9 = findViewById<ImageButton>(R.id.imageButton9)
        val imageButton10 = findViewById<ImageButton>(R.id.imageButton10)
        val imageButton12 = findViewById<ImageButton>(R.id.imageButton12)
        val imageButton13 = findViewById<ImageButton>(R.id.imageButton13)
        val imageButton14 = findViewById<ImageButton>(R.id.imageButton16)
        val imageButton15 = findViewById<ImageButton>(R.id.imageButton15)
        val imageButton17 = findViewById<ImageButton>(R.id.imageButton17)
        val imageButton18 = findViewById<ImageButton>(R.id.imageButton18)
        val imageButton19 = findViewById<ImageButton>(R.id.imageButton19)
        val imageButton20 = findViewById<ImageButton>(R.id.imageButton20)
        val imageButton22 = findViewById<ImageButton>(R.id.imageButton22)
        val imageButton23 = findViewById<ImageButton>(R.id.imageButton23)

        buttons = listOf(
            imageButton9,
            imageButton10,
            imageButton12,
            imageButton13,
            imageButton14,
            imageButton15,
            imageButton17,
            imageButton18,
            imageButton19,
            imageButton20,
            imageButton22,
            imageButton23
        )

        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Log.i(TAG, "button clicked!")
                //Update models
                updateModels(index)
                //Update the UI of game
                updateViews()
            }

        }

        // time count down for 30 seconds,
        // with 1 second as countDown interval
        object : CountDownTimer(30000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                timeText2.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                timeText2.setText("done!")

            }
        }.start()
    }

    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.1f
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else R.drawable.baseline_square_24)
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        //Error Checking
        if (card.isFaceUp) {
            Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show()
            return
        }
        //Three cases
        if (indexOfSingleSelectedCard == null) {
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].identifier == cards[position2].identifier) {
            Toast.makeText(this, "You made a match!", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true
        }
    }
}