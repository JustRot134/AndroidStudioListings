package com.example.qrcode

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

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

        val etText = findViewById<EditText>(R.id.etText)
        val btnCreate = findViewById<Button>(R.id.btnCreate)
        val ivQr = findViewById<ImageView>(R.id.ivQr)
        val tvType = findViewById<TextView>(R.id.tvType)
        val tvLength = findViewById<TextView>(R.id.tvLength)

        btnCreate.setOnClickListener {
            val text = etText.text.toString().trim()

            //Проверка на пустое поле
            if (text.isEmpty()) {
                ivQr.setImageDrawable(null)
                tvType.text = "Сначала введи текст"
                tvType.setTextColor(getColor(R.color.qr_error))
                tvLength.text = ""
                //Проверка на длину текста
            } else if (text.length > 300) {
                ivQr.setImageDrawable(null)
                tvType.text = "Слишком длинный текст"
                tvType.setTextColor(getColor(R.color.black))
                tvLength.text = "Не больше 300 символов, сейчас ${text.length}"
            } else {

                //Создание QR-кода
                val bitmap = BarcodeEncoder().encodeBitmap(
                    text,
                    BarcodeFormat.QR_CODE, 800, 800
                )

                ivQr.setImageBitmap(bitmap)
                //Определяет тип текста
                val type = when {
                    text.startsWith("http://") || text.startsWith("https://") -> "Ссылка"
                    text.contains("@") && !text.contains(" ") -> "Электронная почта"
                    text.startsWith("WIFI:") -> "Сеть Wi-Fi"
                    text.startsWith("+7") || text.startsWith("8") -> "Номер телефона"
                    else -> "Текст"
                }
                //Определяет плотность QR-кода
                val density = when (text.length) {
                    in 1..40 -> "простой"
                    in 41..120 -> "средний"
                    else -> "плотный"
                }
                //Меняет цвет в зависимости от плотности QR-кода
                val color = when (density) {
                    "простой" -> R.color.qr_simple
                    "средний" -> R.color.qr_medium
                    else -> R.color.qr_dense
                }

                tvType.text = type
                tvLength.text = "${text.length} симв. — код $density"
                tvLength.setTextColor(getColor(color))
                tvType.setTextColor(getColor(R.color.black))
            }
        }
    }
}