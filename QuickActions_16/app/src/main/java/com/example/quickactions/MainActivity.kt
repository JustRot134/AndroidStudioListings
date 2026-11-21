package com.example.actions

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quickactions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val pickPhoto =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.ivPhoto.setImageURI(uri)
            } else {
                Toast.makeText(this, "Фото не выбрано", Toast.LENGTH_SHORT).show()
            }
        }

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

        binding.btnCall.setOnClickListener {
            val phone = binding.etPhone.text.toString()
            openIntent(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
        }

        binding.btnOpen.setOnClickListener {
            val url = binding.etUrl.text.toString()
            openIntent(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        binding.btnShare.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                binding.etShare.text.toString())
            openIntent(Intent.createChooser(sendIntent, "Поделиться через"))
        }

        binding.btnMap.setOnClickListener {
            openIntent(Intent(Intent.ACTION_VIEW, Uri.parse("geo:55.7539,37.6208? q=Красная площадь")))
        }

        binding.btnPickPhoto.setOnClickListener {
            pickPhoto.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }
    private fun openIntent(intent: Intent) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Нет приложения для этого действия",
                Toast.LENGTH_SHORT).show()
        }
    }
}