package com.example.multiplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        val etNumber = findViewById<EditText>(R.id.etNumber)
        val btnTable = findViewById<Button>(R.id.btnTable)
        val btnAll = findViewById<Button>(R.id.btnAll)
        val btnPowers = findViewById<Button>(R.id.btnPowers)
        val btnCountdown = findViewById<Button>(R.id.btnCountdown)
        val btnSum = findViewById<Button>(R.id.btnSum)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnTable.setOnClickListener {
            val text = etNumber.text.toString()
            // Проверка на пустое поле
            if (text.isEmpty()) {
                tvResult.text = "Введи число"
            } else {
                val n = text.toInt()

                var result = ""
                for (i in 1..10) {
                    result += "$n × $i = ${n * i}\n"
                }

                tvResult.text = result
            }
        }

        btnAll.setOnClickListener {
            var result = ""

            for (a in 1..10) {
                for (b in 1..10) {
                    result += "$a × $b = ${a * b}\n"
                }
                result += "\n"
            }

            tvResult.text = result
        }

        btnPowers.setOnClickListener {
            var result = ""
            var power = 1
            var step = 0

            while (power <= 1000) {
                result += "2^$step = $power\n"
                power *= 2
                step++
            }

            tvResult.text = result
        }

        btnCountdown.setOnClickListener {
            var result = ""

            for (i in 10 downTo 1) {
                result += "$i\n"
            }
            result += "Старт!"

            tvResult.text = result
        }

        btnCountdown.setOnClickListener {
            var result = ""

            for (i in 10 downTo 1) {
                result += "$i\n"
            }
            result += "Старт!"

            tvResult.text = result
        }

        btnSum.setOnClickListener {
            val text = etNumber.text.toString()
            var result = ""
            var sum = 0

            if (text.isEmpty()) {
                tvResult.text = "Введи число"
            } else {
                val n = text.toInt()

                for (i in 1..n) {
                    sum += i
                }
                result = "Сумма от 1 до $n = $sum"

                tvResult.text = result
            }
        }
    }
}