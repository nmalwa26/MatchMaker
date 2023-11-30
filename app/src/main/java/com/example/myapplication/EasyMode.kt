package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R.drawable.baseline_bedtime_24
import com.example.myapplication.R.drawable.baseline_cloud_24
import com.example.myapplication.R.drawable.baseline_favorite_24
import com.example.myapplication.R.drawable.baseline_star_24

private const val TAG="EasyMode"
private lateinit var buttons: List<ImageButton>
private lateinit var cards: List<MemoryCard>
private var indexOfSingleSelectedCard: Int? = null
class EasyMode : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_mode)
        var timeText = findViewById<TextView>(R.id.TimerText)

        val images = mutableListOf(
            baseline_bedtime_24,
            baseline_cloud_24,
            baseline_favorite_24,
            baseline_star_24
        )
        images.addAll(images)
        images.shuffle()

        val imageButton = findViewById<ImageButton>(R.id.imageButton)
        val imageButton2 = findViewById<ImageButton>(R.id.imageButton2)
        val imageButton3 = findViewById<ImageButton>(R.id.imageButton3)
        val imageButton4 = findViewById<ImageButton>(R.id.imageButton4)
        val imageButton5 = findViewById<ImageButton>(R.id.imageButton5)
        val imageButton6 = findViewById<ImageButton>(R.id.imageButton6)
        val imageButton7 = findViewById<ImageButton>(R.id.imageButton7)
        val imageButton8 = findViewById<ImageButton>(R.id.imageButton8)

        buttons = listOf(
            imageButton,
            imageButton2,
            imageButton3,
            imageButton4,
            imageButton5,
            imageButton6,
            imageButton7,
            imageButton8
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
                timeText.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                timeText.setText("done!")


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
        var counter = 4
        if (cards[position1].identifier == cards[position2].identifier) {
            Toast.makeText(this, "You made a match!", Toast.LENGTH_SHORT).show()
            counter++
            cards[position1].isMatched = true
            cards[position2].isMatched = true
        }

        if(counter.equals(4)){
            //display pop up message saying they won!
        }
    }
}



