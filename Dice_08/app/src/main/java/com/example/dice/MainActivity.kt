package com.example.dice

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

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
        val ivDice1 = findViewById<ImageView>(R.id.ivDice1)
        val ivDice2 = findViewById<ImageView>(R.id.ivDice2)
        val ivRollCount = findViewById<TextView>(R.id.rollCount)
        val ivBraceCount = findViewById<TextView>(R.id.braceCount)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnRoll = findViewById<Button>(R.id.btnRoll)

        var rollCount = 0
        var braceCount = 0

        btnRoll.setOnClickListener {
            val dice1 = Random.nextInt(1, 7)
            val dice2 = Random.nextInt(1, 7)

            ivDice1.setImageResource(diceImage(dice1))
            ivDice2.setImageResource(diceImage(dice2))

            val sum = dice1 + dice2

            if (dice1 == dice2) {
                tvResult.text = "Сумма: $sum\nДубль!"
                braceCount = braceCount + 1
                ivBraceCount.text = "Дублей: $braceCount"
            } else {
                tvResult.text = "Сумма: $sum"
                rollCount = rollCount + 1
                ivRollCount.text = "Бросков: $rollCount"
            }
        }
    }

    private fun diceImage(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}