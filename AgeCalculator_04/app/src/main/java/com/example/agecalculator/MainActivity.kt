package com.example.agecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

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

        val etYear = findViewById<EditText>(R.id.etYear)
        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        btnCalc.setOnClickListener {

            val yearText: String = etYear.text.toString()
            val birthYear: Int = yearText.toInt()
            val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
            val age: Int = currentYear - birthYear
            val months: Int = age * 12
            val days: Int = age * 365
            val hours: Int = days * 24
            val minutes: Int = hours * 60
            val sleepYears: Double = age * 8 / 24.0
            val nextYear: Int = age + 1

            tvResult.text = "Полных лет: $age\n" +
                    "Месяцев: $months\n" +
                    "Дней: примерно $days\n" +
                    "Часов: примерно $hours\n" +
                    "Минут: примерно $minutes\n" +
                    "В следующем году: $nextYear\n" +
                    "Из них во сне, лет: " + "%.1f".format(sleepYears)
        }
    }
}