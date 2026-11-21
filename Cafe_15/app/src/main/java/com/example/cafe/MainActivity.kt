package com.example.cafe

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cafe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val dishes = listOf(
        Dish("Пицца", 450, "Тонкое тесто, томатный соус, моцарелла и базилик.", "🍕"),
        Dish("Бургер", 390, "Говяжья котлета, сыр чеддер, свежие овощи.", ""),
        Dish("Салат", 290, "Листья салата, помидоры, огурцы, оливковое масло.", ""),
        Dish("Мороженное", 180, "Три шарика: ваниль, шоолад и клубника.", "")
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
        val buttons = listOf(binding.btnDish1, binding.btnDish2, binding.btnDish3,
            binding.btnDish4)

        for (i in buttons.indices) {
            val dish = dishes[i]
            buttons[i].text = "${dish.emoji} ${dish.name} — ${dish.price} ₽"
            buttons[i].setOnClickListener { openDish(dish) }
        }
    }
    private fun openDish(dish: Dish) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_NAME, dish.name)
        intent.putExtra(EXTRA_PRICE, dish.price)
        intent.putExtra(EXTRA_DESCRIPTION, dish.description)
        intent.putExtra(EXTRA_EMOJI, dish.emoji)
        startActivity(intent)
    }
}