package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityScientificCalculatorBinding
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class ScientificCalculatorActivity : AppCompatActivity() {
    private var previousNumber = ""
    private var currentNumber = "0"
    private var currentOperation = ""
    private var isAfterClear = false

    lateinit var binding: ActivityScientificCalculatorBinding

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("previousNumber", previousNumber)
        outState.putString("currentNumber", currentNumber)
        outState.putString("currentOperation", currentOperation)
        outState.putBoolean("isAfterClear", isAfterClear)
        outState.putString("tvResults", binding.tvResults.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        previousNumber = savedInstanceState.getString("previousNumber", "")
        currentNumber = savedInstanceState.getString("currentNumber", "0")
        currentOperation = savedInstanceState.getString("currentOperation", "")
        isAfterClear = savedInstanceState.getBoolean("isAfterClear", false)
        binding.tvResults.text = savedInstanceState.getString("tvResults", "0")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScientificCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun isShowingArithmetic(): Boolean {
            if (binding.tvResults.text == "*" || binding.tvResults.text == "/" ||
                binding.tvResults.text == "-" || binding.tvResults.text == "+" ||
                binding.tvResults.text == "^") {
                return true
            }
            return false
        }

        fun checkIfNumberIsInt(number: Double): String {
            if (number == number.toInt().toDouble()) {
                return number.toInt().toString()
            }
            return number.toString()
        }

        // Scientific

        binding.sin.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentNumber = sin(currentNumber.toDouble()).toString()
                currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
                binding.tvResults.text = currentNumber
            }
        }

        binding.cos.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentNumber = cos(currentNumber.toDouble()).toString()
                currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
                binding.tvResults.text = currentNumber
            }
        }

        binding.tan.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentNumber = tan(currentNumber.toDouble()).toString()
                currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
                binding.tvResults.text = currentNumber
            }
        }

        binding.log.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentNumber = log(currentNumber.toDouble(), 10.0).toString()
                currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
                binding.tvResults.text = currentNumber
            }
        }

        binding.ln.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentNumber = ln(currentNumber.toDouble()).toString()
                currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
                binding.tvResults.text = currentNumber
            }
        }

        binding.sqrt.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentNumber = sqrt(currentNumber.toDouble()).toString()
                currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
                binding.tvResults.text = currentNumber
            }
        }

        binding.powerDouble.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentNumber = currentNumber.toDouble().pow(2.0).toString()
                currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
                binding.tvResults.text = currentNumber
            }
        }

        binding.powerY.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentOperation = "^"
                if (!isAfterClear) {
                    previousNumber = currentNumber
                }
                isAfterClear = false
                currentNumber = "0"
                binding.tvResults.text = currentOperation
            }
        }

        binding.percent.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentNumber = (currentNumber.toDouble() / 100).toString()
                currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
                binding.tvResults.text = currentNumber
            }
        }

        // Basic

        fun equals() {
            if (previousNumber == "" || currentNumber == "" || isShowingArithmetic() || currentOperation == "") {
                return
            }
            var result = 0.0
            when (currentOperation) {
                "+" -> result = previousNumber.toDouble() + currentNumber.toDouble()
                "-" -> result = previousNumber.toDouble() - currentNumber.toDouble()
                "*" -> result = previousNumber.toDouble() * currentNumber.toDouble()
                "/" -> result = previousNumber.toDouble() / currentNumber.toDouble()
                "^" -> result = previousNumber.toDouble().pow(currentNumber.toDouble())
            }
            currentNumber = checkIfNumberIsInt(result)
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
            if (isShowingArithmetic()) {
                currentOperation = operation
                binding.tvResults.text = currentOperation
                return
            }
            equals()
            currentOperation = operation
            if (!isAfterClear) {
                previousNumber = currentNumber
            }
            isAfterClear = false
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

        binding.clear.setOnClickListener {
            currentNumber = "0"
            binding.tvResults.text = "0"
            currentOperation = ""
            isAfterClear = true
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

        // Other

        binding.dot.setOnClickListener {
            if (!isShowingArithmetic()) {
                if (!currentNumber.contains('.')) {
                    currentNumber += "."
                    binding.tvResults.text = currentNumber
                } else if (currentNumber.last() == '.') {
                    currentNumber = currentNumber.toDouble().toInt().toString()
                    binding.tvResults.text = currentNumber
                }
            }
        }

        binding.plusMinus.setOnClickListener {
            if (!isShowingArithmetic()) {
                currentNumber = (-(currentNumber.toDouble())).toString()
                val result = currentNumber.toDouble()
                currentNumber = checkIfNumberIsInt(result)
                binding.tvResults.text = currentNumber
            }
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}