package com.abs.diceroller

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val image1 = findViewById<ImageView>(R.id.dice_iv1)
        val image2: ImageView = findViewById(R.id.dice_iv2)
        val  btn: Button = findViewById(R.id.roll_btn)

        btn.setOnClickListener {
            // 1. Set the animation drawable as the source for both ImageViews
            image1.setImageResource(R.drawable.animation_file)
            image2.setImageResource(R.drawable.animation_file)
            // 2. Get and start the animation for the first die
            val diceAnimation = image1.drawable as AnimationDrawable
            diceAnimation.start()
            // 3. Get and start the animation for the second die
            val diceAnimation2 = image2.drawable as AnimationDrawable
            diceAnimation2.start()
            // 4. Use a Handler to delay showing the final result.
            Handler(Looper.getMainLooper()).postDelayed({
                image1.setImageResource(rollDice())
                image2.setImageResource(rollDice())
            }, 600)
        }
    }

    fun rollDice(): Int {
        val randomNom = (1..6).random()
        return when(randomNom){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}