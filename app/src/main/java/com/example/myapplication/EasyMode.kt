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

        //initializes text for timer
        var timeText = findViewById<TextView>(R.id.TimerText)

        //creates a home button that leads to home page
        val homeButton = findViewById<ImageButton>(R.id.homeButton1)
        homeButton.setOnClickListener{
            val Intent = Intent(this,MainActivity::class.java)
            startActivity(Intent)
        }

        //initialize match icons
        val images = mutableListOf(
            baseline_bedtime_24,
            baseline_cloud_24,
            baseline_favorite_24,
            baseline_star_24
        )
        //duplicates images so match can be made
        images.addAll(images)
        //order of images will change each time app runs
        images.shuffle()

        //initialize buttons(front side of card)
        val imageButton = findViewById<ImageButton>(R.id.imageButton)
        val imageButton2 = findViewById<ImageButton>(R.id.imageButton2)
        val imageButton3 = findViewById<ImageButton>(R.id.imageButton3)
        val imageButton4 = findViewById<ImageButton>(R.id.imageButton4)
        val imageButton5 = findViewById<ImageButton>(R.id.imageButton5)
        val imageButton6 = findViewById<ImageButton>(R.id.imageButton6)
        val imageButton7 = findViewById<ImageButton>(R.id.imageButton7)
        val imageButton8 = findViewById<ImageButton>(R.id.imageButton8)

        //sort buttons together into a list
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
                timeText.setText("Time: " + millisUntilFinished / 1000)
            }

            // when time finishes
            override fun onFinish() {
                //go to lose page
                setContentView(R.layout.loser_page)
                //button that leads back to the home page
                val btn = findViewById<Button>(R.id.hp)
                btn.setOnClickListener {
                    val Intent1 = Intent(this@EasyMode,MainActivity::class.java)
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
        if(counter.equals(4)){
            setContentView(R.layout.winner_page)
            val Intent1 = Intent(this@EasyMode,winnerPage::class.java)
            startActivity(Intent1)
        }
    }
}



