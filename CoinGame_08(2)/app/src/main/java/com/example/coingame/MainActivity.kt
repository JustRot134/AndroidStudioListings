package com.example.coingame

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
        val ivCoin = findViewById<ImageView>(R.id.ivCoin)
        val tvRollCount = findViewById<TextView>(R.id.rollCount)
        val tvHeadsCount = findViewById<TextView>(R.id.headsCount)
        val tvTailsCount = findViewById<TextView>(R.id.tailsCount)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnRoll = findViewById<Button>(R.id.btnRoll)

        var rollCount = 0
        var headsCount = 0
        var tailsCount = 0

        btnRoll.setOnClickListener {
            rollCount += 1
            tvRollCount.text = "Бросков: $rollCount"

            if (Random.nextBoolean()) {
                ivCoin.setImageResource(coinImage(1))
                tvResult.text = "Орёл"
                headsCount += 1
                tvHeadsCount.text = "Орлов: $headsCount"
            } else {
                ivCoin.setImageResource(coinImage(2))
                tvResult.text = "Решка"
                tailsCount += 1
                tvTailsCount.text = "Решек: $tailsCount"
            }
        }
    }
    private fun coinImage(value: Int): Int {
        return when (value) {
            1 -> R.drawable.heads
            else -> R.drawable.tails
        }
    }
}