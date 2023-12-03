package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
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

<<<<<<< Updated upstream
        val images = mutableListOf(baseline_bedtime_24, baseline_cloud_24, baseline_favorite_24, baseline_star_24)

        //initialize match icons
        val images = mutableListOf(
            baseline_bedtime_24,
            baseline_cloud_24,
            baseline_favorite_24,
            baseline_star_24
        )
        //duplicates images so match can be made
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        buttons = listOf(imageButton,imageButton2,imageButton3, imageButton4, imageButton5, imageButton6, imageButton7, imageButton8)

        cards = buttons.indices.map{ index ->
            MemoryCard(images[index])
        }

        buttons.forEachIndexed{ index, button->
=======
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
           //access memory card class
            //creates memory card for each button & sets default for each card
            MemoryCard(images[index])
        }

        //attach click listener to each button
        buttons.forEachIndexed { index, button ->
>>>>>>> Stashed changes
            button.setOnClickListener {
                //when user clicks button, accesses this code
                Log.i(TAG, "button clicked!")
                //Update models
                updateModels(index)
                //Update the UI of game
                updateViews()
            }

        }
<<<<<<< Updated upstream
    }

    private fun updateViews() {
        cards.forEachIndexed{ index, card ->
            val button = buttons[index]
=======

        // time count down for 30 seconds, with 1 second as countDown interval
        object : CountDownTimer(30000, 1000) {

            // Callback function, executes on regular interval
            override fun onTick(millisUntilFinished: Long) {
                timeText.setText("Time: " + millisUntilFinished / 1000)
            }

            // Callback function, executes when the time is up
            override fun onFinish() {
                timeText.setText("done!")
                //displays message indicating time has elapsed
            }
        }.start()
    }

    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index] //iterates through every button
>>>>>>> Stashed changes
            if (card.isMatched) {
                button.alpha = 0.1f  //fades buttons from screen if match is found
            }
            //if button is clicked, displays match icon on backside
            //otherwise maintains display of black square
            button.setImageResource(if (card.isFaceUp) card.identifier else R.drawable.baseline_square_24)
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
<<<<<<< Updated upstream
        //Error Checking
        if(card.isFaceUp){
            Toast.makeText(this,"Invalid move",Toast.LENGTH_SHORT).show()
            return
        }
        //Three cases
        if (indexOfSingleSelectedCard == null){
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
          checkForMatch(indexOfSingleSelectedCard!!, position)
          indexOfSingleSelectedCard = null
=======
        //error checking; if user taps an already flipped card  error message displayed
        if (card.isFaceUp) {
            Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show()
            return
        }
        //three conditions (0 cards, 1 card, or 2 cards already flipped over)
        if (indexOfSingleSelectedCard == null) {
           //when 0 or 2 cards already flipped
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            //when 1 card already flipped
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
>>>>>>> Stashed changes
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
<<<<<<< Updated upstream
        for (card in cards){
            if (!card.isMatched){
                card.isFaceUp = false
            }
=======
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false  //if a match is not found, "flips card back over"
            }                          //by restoring image of black square
>>>>>>> Stashed changes
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
<<<<<<< Updated upstream
   if (cards[position1].identifier == cards[position2].identifier){
       Toast.makeText(this, "You made a match!", Toast.LENGTH_SHORT).show()
       cards[position1].isMatched = true
       cards[position2].isMatched = true
   }
=======
        var counter = 4
        //if the two cards are the same; match is made & displays message
        if (cards[position1].identifier == cards[position2].identifier) {
            Toast.makeText(this, "You made a match!", Toast.LENGTH_SHORT).show()
            counter++
            cards[position1].isMatched = true
            cards[position2].isMatched = true
        }

        if(counter.equals(4)){
            //displays pop up message saying they won!
        }
>>>>>>> Stashed changes
    }
}