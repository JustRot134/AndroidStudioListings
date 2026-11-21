package com.example.signup

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.signup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                systemBars.bottom)
            insets
        }
        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text?.toString() ?: ""
            val email = binding.etEmail.text?.toString() ?: ""
            val ageText = binding.etAge.text?.toString() ?: ""
            val password = binding.etPassword.text?.toString() ?: ""
            val repeat = binding.etRepeat.text?.toString() ?: ""

            binding.tilName.error = validateName(name)
            binding.tilEmail.error = validateEmail(email)
            binding.tilAge.error = validateAge(ageText)
            binding.tilPassword.error = validatePassword(password)
            binding.tilRepeat.error = if (repeat != password) "Пароли не совпадают" else null

            val allValid = binding.tilName.error == null &&
                    binding.tilEmail.error == null &&
                    binding.tilAge.error == null &&
                    binding.tilPassword.error == null &&
                    binding.tilRepeat.error == null
        }
    }
    private fun validateName(name: String): String? {
        if (name.length < 2) return "Имя — минимум 2 буквы"
        return null
    }

    private fun validateEmail(email: String): String? {
        if (email.isEmpty()) return "Введи email"
        if (!email.contains("@") || !email.contains(".")) return "Похоже, это не email"
        return null
    }

    private fun validateAge(text: String): String? {
        val age: Int? = text.toIntOrNull()

        if (age == null) return "Введи возраст числом"
        if (age < 14 || age > 120) return "Возраст — от 14 до 120"
        return null
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.length < 6 -> "Минимум 6 символов"
            !password.any { it.isDigit() } -> "Нужна хотя бы одна цифра"
            else -> null
        }
    }
}