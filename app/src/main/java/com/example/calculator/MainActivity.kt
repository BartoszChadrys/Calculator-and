package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var previousNumber = ""
    private var currentNumber = "0"
    private var currentOperation = ""

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Equals function

        fun equals() {
            if (previousNumber == "" || currentNumber == "") {
                return
            }
            var result = 0.0
            when (currentOperation) {
                "+" -> result = previousNumber.toDouble() + currentNumber.toDouble()
                "-" -> result = previousNumber.toDouble() - currentNumber.toDouble()
                "*" -> result = previousNumber.toDouble() * currentNumber.toDouble()
                "/" -> result = previousNumber.toDouble() / currentNumber.toDouble()
            }
            if (result.toInt().toDouble() == result) {
                currentNumber = result.toInt().toString()
            } else {
                currentNumber = result.toString()
            }
            binding.tvResults.text = currentNumber
            currentOperation = ""
            previousNumber = ""
        }

        // Updating

        fun updateNumber(number: String) {
            if (currentNumber == "0") {
                currentNumber = number
            } else {
                currentNumber += number
            }
            binding.tvResults.text = currentNumber
        }

        fun updateOperation(operation: String) {
            if (binding.tvResults.text == "*" || binding.tvResults.text == "/" ||
                binding.tvResults.text == "-" || binding.tvResults.text == "+") {
                currentOperation = operation
                binding.tvResults.text = currentOperation
                return
            }
            equals()
            currentOperation = operation
            previousNumber = currentNumber
            currentNumber = "0"
            binding.tvResults.text = currentOperation
        }

        // Clearing

        binding.allClear.setOnClickListener {
            previousNumber = ""
            currentNumber = "0"
            currentOperation = ""
            binding.tvResults.text = "0"
        }

        // Numbers

        binding.zero.setOnClickListener { updateNumber("0") }
        binding.one.setOnClickListener { updateNumber("1") }
        binding.two.setOnClickListener { updateNumber("2") }
        binding.three.setOnClickListener { updateNumber("3") }
        binding.four.setOnClickListener { updateNumber("4") }
        binding.five.setOnClickListener { updateNumber("5") }
        binding.six.setOnClickListener { updateNumber("6") }
        binding.seven.setOnClickListener { updateNumber("7") }
        binding.eight.setOnClickListener { updateNumber("8") }
        binding.nine.setOnClickListener { updateNumber("9") }

        // Arithmetic

        binding.plus.setOnClickListener { updateOperation("+") }
        binding.minus.setOnClickListener { updateOperation("-") }
        binding.multiply.setOnClickListener { updateOperation("*") }
        binding.divide.setOnClickListener { updateOperation("/") }
        binding.equals.setOnClickListener { equals() }
    }
}