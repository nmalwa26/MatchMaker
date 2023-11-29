package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R.drawable.*
import com.example.myapplication.R.drawable.baseline_headset_24
import com.example.myapplication.R.drawable.baseline_icecream_24
import com.example.myapplication.R.drawable.baseline_fastfood_24
import com.example.myapplication.R.drawable.baseline_bedtime_24
import com.example.myapplication.R.drawable.baseline_castle_24
import com.example.myapplication.R.drawable.baseline_cloud_24
import com.example.myapplication.R.drawable.baseline_electric_car_24
import com.example.myapplication.R.drawable.baseline_favorite_24
import com.example.myapplication.R.drawable.baseline_star_24

private const val TAG="HardMode"
private lateinit var buttons: List<ImageButton>
private lateinit var cards: List<MemoryCard>
private var indexOfSingleSelectedCard: Int? = null
class HardMode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_mode)

        val images = mutableListOf( baseline_bedtime_24, baseline_cloud_24, baseline_favorite_24, baseline_star_24,
            baseline_electric_car_24, baseline_castle_24, baseline_fastfood_24, baseline_headset_24,
            baseline_icecream_24)
        images.addAll(images)
        images.shuffle()

        val imageButton25 = findViewById<ImageButton>(R.id.imageButton25)
        val imageButton26 = findViewById<ImageButton>(R.id.imageButton26)
        val imageButton27 = findViewById<ImageButton>(R.id.imageButton27)
        val imageButton28 = findViewById<ImageButton>(R.id.imageButton28)
        val imageButton29 = findViewById<ImageButton>(R.id.imageButton29)
        val imageButton30 = findViewById<ImageButton>(R.id.imageButton30)
        val imageButton31 = findViewById<ImageButton>(R.id.imageButton31)
        val imageButton32 = findViewById<ImageButton>(R.id.imageButton32)
        val imageButton33 = findViewById<ImageButton>(R.id.imageButton33)
        val imageButton34 = findViewById<ImageButton>(R.id.imageButton34)
        val imageButton35 = findViewById<ImageButton>(R.id.imageButton35)
        val imageButton36 = findViewById<ImageButton>(R.id.imageButton36)
        val imageButton37 = findViewById<ImageButton>(R.id.imageButton37)
        val imageButton38 = findViewById<ImageButton>(R.id.imageButton38)
        val imageButton39 = findViewById<ImageButton>(R.id.imageButton39)
        val imageButton40 = findViewById<ImageButton>(R.id.imageButton40)
        val imageButton41 = findViewById<ImageButton>(R.id.imageButton41)



        buttons = listOf(
            imageButton25,
            imageButton26,
            imageButton27,
            imageButton28,
            imageButton29,
            imageButton30,
            imageButton31,
            imageButton32,
            imageButton33,
            imageButton34,
            imageButton35,
            imageButton36,
            imageButton37,
            imageButton38,
            imageButton39,
            imageButton40,
            imageButton41
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