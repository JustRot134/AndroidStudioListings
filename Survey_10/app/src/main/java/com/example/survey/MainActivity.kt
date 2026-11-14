package com.example.survey

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.RadioGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch

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
        val etName = findViewById<EditText>(R.id.etName)
        val rgAge = findViewById<RadioGroup>(R.id.rgAge)
        val cbSport = findViewById<CheckBox>(R.id.cbSport)
        val cbMusic = findViewById<CheckBox>(R.id.cbMusic)
        val cbGames = findViewById<CheckBox>(R.id.cbGames)
        val cbBooks = findViewById<CheckBox>(R.id.cbBooks)
        val spCity = findViewById<Spinner>(R.id.spCity)
        val swSubscribe = findViewById<MaterialSwitch>(R.id.swSubscribe)
        val tvSummary = findViewById<TextView>(R.id.tvSummary)

        swSubscribe.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked == true) Toast.makeText(this, "Спасибо за подписку!", Toast.LENGTH_SHORT).show() }

        findViewById<Button>(R.id.btnSend).setOnClickListener {
            val name = etName.text.toString()

            if (name.isEmpty()) {
                tvSummary.text = "Укажите имя"
                return@setOnClickListener
            }

            val age = when (rgAge.checkedRadioButtonId) {
                R.id.rbUnder18 -> "до 18"
                R.id.rb18to25 -> "18–25"
                else -> "старше 25"
            }

            var interests = ""
            if (cbSport.isChecked) interests += "спорт "
            if (cbMusic.isChecked) interests += "музыка "
            if (cbGames.isChecked) interests += "игры "
            if (cbBooks.isChecked) interests += "книги "
            if (interests.isEmpty()) interests = "не выбраны"

            val city = spCity.selectedItem.toString()
            val subscribe = if (swSubscribe.isChecked) "да" else "нет"

            tvSummary.text = "Имя: $name\n" +
                    "Возраст: $age\n" +
                    "Интересы: $interests\n" +
                    "Город: $city\n" +
                    "Подписка: $subscribe"
        }
    }
}