package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
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

        //initializes text for timer
        var timeText2 = findViewById<TextView>(R.id.TimerText2)

        //creates a home button that leads to home page
        val homeButton = findViewById<ImageButton>(R.id.homeButton2)
        homeButton.setOnClickListener{
            val Intent = Intent(this,MainActivity::class.java)
            startActivity(Intent)
        }

        //initialize match icons
        val images = mutableListOf(
            baseline_bedtime_24,
            baseline_cloud_24,
            baseline_favorite_24,
            baseline_star_24,
            baseline_electric_car_24,
            baseline_castle_24)
        //duplicates images so match can be made
        images.addAll(images)
        //order of images will change each time app runs
        images.shuffle()

        //initialize buttons(front side of card)
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

        //sort buttons together into a list
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

        //operates on given list of buttons
        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }

        //attach click listener to each button
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Log.i(TAG, "button clicked!")
                //Updates game based on what cards selected
                updateModels(index)
                //Updates the view of the cards
                updateViews()
            }

        }

        // time count down for 30 seconds,
        // with 1 second as countDown interval
        object : CountDownTimer(30000, 1000) {

            // Displays time counting down
            override fun onTick(millisUntilFinished: Long) {
                timeText2.setText("Time: " + millisUntilFinished / 1000)
            }

            //when time finishes
            override fun onFinish() {
                //go to lose page
                setContentView(R.layout.loser_page)
                //button that leads back to the home page
                val btn = findViewById<Button>(R.id.hp)
                btn.setOnClickListener {
                    val Intent1 = Intent(this@MediumMode,MainActivity::class.java)
                    startActivity(Intent1)
                }

            }
        }.start()
    }

    private fun updateViews() {
        //iterates through every button
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.1f
            }
            //fades matched cards
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
        //If 0 or 2 cards flipped over before next card clicked
        if (indexOfSingleSelectedCard == null) {
            restoreCards()
            indexOfSingleSelectedCard = position
        }
        //If 1 card is flipped over before next card clicked
        else {
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
    }

    //flips cards back if turned over
    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    //initializes counter variable
    private var counter = 0

    //checks for a match
    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].identifier == cards[position2].identifier) {
            //message displaying that a match was made
            Toast.makeText(this, "You made a match!", Toast.LENGTH_SHORT).show()
            counter++
            cards[position1].isMatched = true
            cards[position2].isMatched = true
        }

        //checks if all matches are made
        //opens winner page if completed in time
        if(counter.equals(6)){
            setContentView(R.layout.winner_page)
            //button that leads back to home page
            val btn = findViewById<Button>(R.id.hp)
            btn.setOnClickListener {
                val Intent1 = Intent(this@MediumMode,MainActivity::class.java)
                startActivity(Intent1)
            }
        }
    }
}