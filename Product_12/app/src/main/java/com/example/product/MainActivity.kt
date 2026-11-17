package com.example.product

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.product.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val products = listOf(
        Product("Смартфон", 24990, 10,4.7, true, "📱"),
        Product("Наушники", 5490, 15,4.5, true, "🎧"),
        Product("Умные часы", 12990, 30,4.2, false, "⌚"),
        Product("Ноутбук", 69990, 10,4.8, true, "💻")
    )

    private val cart = Cart()

    private var currentIndex = 0

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
        showProduct(products[currentIndex])

        binding.btnPast.setOnClickListener {
            currentIndex--
            if (currentIndex == -1) currentIndex = 3 // по кругу
            showProduct(products[currentIndex])
        }

        binding.btnNext.setOnClickListener {
            currentIndex++
            if (currentIndex == products.size) currentIndex = 0 // по кругу
            showProduct(products[currentIndex])
        }

        binding.btnAdd.setOnClickListener {
            val product = products[currentIndex]

            if (product.inStock) {
                cart.add(product)
                showCart()
            } else {
                Toast.makeText(this, "${product.name}: нет в наличии",
                    Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            cart.clear()
            showCart()
        }
    }
    private fun showProduct(product: Product) {
        binding.tvEmoji.text = product.emoji
        binding.tvName.text = product.name
        binding.tvDiscount.text = "-${product.discount}%"
        binding.tvPrice.text = "${product.price} ₽"
        binding.tvNewPrice.text = "${cart.newPrice(product)} ₽"
        binding.tvRating.text = "★ ${product.rating}"

        if (product.inStock) {
            binding.tvStock.text = "В наличии"
            binding.tvStock.setTextColor(getColor(R.color.in_stock))
        } else {
            binding.tvStock.text = "Нет в наличии"
            binding.tvStock.setTextColor(getColor(R.color.out_of_stock))
        }
    }
    private fun showCart() {
        binding.tvCart.text = "В корзине: ${cart.count()} шт. на ${cart.total()}₽"
    }
}