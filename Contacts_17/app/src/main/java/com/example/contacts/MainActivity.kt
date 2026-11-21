package com.example.contacts

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val contacts = listOf(
        Contact("Анна Смирнова", "+7 900 123-45-67"),
        Contact("Борис Иванов", "+7 901 234-56-78"),
        Contact("Вера Кузнецова", "+7 902 345-67-89"),
        Contact("Григорий Попов", "+7 903 456-78-90"),
        Contact("Дарья Соколова", "+7 904 567-89-01"),
        Contact("Евгений Лебедев", "+7 905 678-90-12"),
        Contact("Жанна Козлова", "+7 906 789-01-23"),
        Contact("Захар Новиков", "+7 907 890-12-34"),
        Contact("Ирина Морозова", "+7 908 901-23-45"),
        Contact("Кирилл Волков", "+7 909 012-34-56"),
        Contact("Лидия Павлова", "+7 910 123-45-67"),
        Contact("Максим Семёнов", "+7 911 234-56-78"),
        Contact("Наталья Голубева", "+7 912 345-67-89"),
        Contact("Олег Виноградов", "+7 913 456-78-90"),
        Contact("Полина Богданова", "+7 914 567-89-01")
    )

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

        binding.rvContacts.layoutManager = LinearLayoutManager(this)

        binding.rvContacts.addItemDecoration(DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL))

        binding.rvContacts.adapter = ContactAdapter(contacts)
    }
}