package com.example.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val tvError = findViewById<TextView>(R.id.tvError)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        var attempts = 0

        btnLogin.setOnClickListener {
            val login = etLogin.text.toString()
            val password = etPassword.text.toString()

            if (login.isEmpty() || password.isEmpty()) {
                tvError.text = "Заполни логин и пароль"
            } else if (login == "student" && password == "12345") {
                tvError.text = ""
                Toast.makeText(this, "Добро пожаловать, $login!", Toast.LENGTH_SHORT).show()
            } else {
                tvError.text = "Неверный логин или пароль"
                attempts += 1

                if (attempts == 3) {
                    btnLogin.isEnabled = false
                    tvError.text = "Слишком много попыток" }
            }
        }

        findViewById<TextView>(R.id.tvForgot).setOnClickListener {
            Toast.makeText(this, "Подсказка: student / 12345", Toast.LENGTH_LONG).show()
        }
    }
}