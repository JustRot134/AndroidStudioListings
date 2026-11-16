package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var firstNumber = 0.0
    private var operation = ""
    private var startNewNumber = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val digitButtons = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9
        )
        for (button in digitButtons) {
            button.setOnClickListener { onDigit(button.text.toString()) }
        }


        val operationButtons = listOf(
            binding.btnPlus, binding.btnMinus, binding.btnMultiply,
            binding.btnDivide, binding.btnDivide100
        )
        for (button in operationButtons) {
            button.setOnClickListener { onOperation(button.text.toString()) }
        }

        binding.btnDot.setOnClickListener { onDot() }
        binding.btnEquals.setOnClickListener { onEquals() }
        binding.btnClear.setOnClickListener { onClear() }
        binding.btnErase.setOnClickListener { onErase() }
    }


    private fun onDigit(digit: String) {
        if (startNewNumber || binding.tvDisplay.text.toString() == "0") {
            binding.tvDisplay.text = digit
            startNewNumber = false
        } else {
            binding.tvDisplay.append(digit)
        }
    }

    private fun onDot() {
        if (startNewNumber) {
            binding.tvDisplay.text = "0."
            startNewNumber = false
        } else if (!binding.tvDisplay.text.contains(".")) {
            binding.tvDisplay.append(".")
        }
    }

    private fun onOperation(op: String) {
        if (binding.tvDisplay.text.toString() == "Ошибка") return

        firstNumber = binding.tvDisplay.text.toString().toDouble()
        operation = op
        startNewNumber = true
    }

    private fun onEquals() {
        if (operation.isEmpty() || binding.tvDisplay.text.toString() == "Ошибка") return
        val secondNumber = binding.tvDisplay.text.toString().toDouble()

        if (operation == "÷" && secondNumber == 0.0) {
            binding.tvDisplay.text = "Ошибка"
            operation = ""
            startNewNumber = true
            return
        }
        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "−" -> firstNumber - secondNumber
            "×" -> firstNumber * secondNumber
            "%" -> firstNumber / 100
            else -> firstNumber / secondNumber
        }
        binding.tvDisplay.text = formatNumber(result)
        operation = ""
        startNewNumber = true
    }

    private fun onClear() {
        binding.tvDisplay.text = "0"
        firstNumber = 0.0
        operation = ""
        startNewNumber = true
    }

    private fun onErase() {
        val new = binding.tvDisplay.text.toString().dropLast(1)
        binding.tvDisplay.text = new.ifEmpty { "0" }
    }

    private fun formatNumber(value: Double): String {
        return if (value == value.toLong().toDouble()) {
            value.toLong().toString()
        } else {
            value.toString()
        }
    }
}