package com.example.cafe

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cafe.databinding.ActivityDetailBinding
import kotlin.toString

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra(EXTRA_NAME) ?: ""
        val price = intent.getIntExtra(EXTRA_PRICE, 0)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION) ?: ""
        val emoji = intent.getStringExtra(EXTRA_EMOJI) ?: ""
        binding.tvEmoji.text = emoji
        binding.tvName.text = name
        binding.tvDescription.text = description
        showTotal(price)
        binding.btnPlus.setOnClickListener {
            quantity++
            showTotal(price)
        }
        binding.btnMinus.setOnClickListener {
            if (quantity > 1) quantity--
            showTotal(price)
        }
        binding.btnOrder.setOnClickListener {
            Toast.makeText(this, "Заказ принят: $name × $quantity",
                Toast.LENGTH_SHORT).show()
            finish() // закрыть этот экран и вернуться к меню
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
    private fun showTotal(price: Int) {
        binding.tvQuantity.text = quantity.toString()
        binding.tvTotal.text = "Итого: ${price * quantity} ₽"
    }
}

