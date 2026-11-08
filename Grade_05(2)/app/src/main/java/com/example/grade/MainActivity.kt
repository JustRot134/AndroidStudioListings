package com.example.grade

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

        val etScore = findViewById<EditText>(R.id.etScore)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val tvGrade = findViewById<TextView>(R.id.tvGrade)
        val tvComment = findViewById<TextView>(R.id.tvComment)

        btnCheck.setOnClickListener {
            val text = etScore.text.toString()

            if (text.isEmpty()) {
                tvGrade.text = "?"
                tvGrade.setTextColor(getColor(R.color.gray))
                tvComment.text = "Сначала введи баллы"
            } else {
                val score = text.toInt()

                if (score < 0 || score > 100) {
                    tvGrade.text = "?"
                    tvGrade.setTextColor(getColor(R.color.gray))
                    tvComment.text = "Баллы должны быть от 0 до 100"
                } else {
                    val grade = when (score) {
                        100 -> "5+"
                        in 85..100 -> 5
                        in 70..84 -> 4
                        in 50..69 -> 3
                        else -> 2
                    }
                    val comment = when (grade) {
                        "5+" -> "Идеально!"
                        5 -> "Отлично!"
                        4 -> "Хорошо"
                        3 -> "Удовлетворительно"
                        else -> "Нужно подтянуть"
                    }
                    val color = when (grade) {
                        "5+", 5, 4 -> R.color.grade_good
                        3 -> R.color.grade_ok
                        else -> R.color.grade_bad
                    }

                    tvGrade.text = grade.toString()
                    tvComment.text = comment
                    tvGrade.setTextColor(getColor(color))
                }
            }
        }
    }
}