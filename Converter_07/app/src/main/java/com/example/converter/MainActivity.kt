package com.example.converter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val usdRate = 82.5
    private val eurRate = 95.0
    private val cnyRate = 11.4
    private val kztRate = 0.17

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etRubles = findViewById<EditText>(R.id.etRubles)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        findViewById<Button>(R.id.btnUsd).setOnClickListener {
            showResult(etRubles, tvResult, usdRate, "$")
        }
        findViewById<Button>(R.id.btnEur).setOnClickListener {
            showResult(etRubles, tvResult, eurRate, "€")
        }
        findViewById<Button>(R.id.btnCny).setOnClickListener {
            showResult(etRubles, tvResult, cnyRate, "¥")
        }
        findViewById<Button>(R.id.btnKzt).setOnClickListener {
            showResult(etRubles, tvResult, kztRate, "₸")
        }
    }

    private fun convert(rubles: Double, rate: Double): Double { return rubles / rate }

    private fun formatMoney(amount: Double, symbol: String): String {
        return "%.2f %s".format(amount, symbol)
    }

    private fun showResult(etRubles: EditText, tvResult: TextView, rate: Double, symbol: String) {
        val text = etRubles.text.toString()

        if (text.isEmpty()) {
            tvResult.text = "Введи сумму"
            return
        }

        val rubles = text.replace(",", ".").toDouble()
        val result = convert(rubles, rate)

        tvResult.text = "$text = ${formatMoney(result, symbol)}"
    }
}