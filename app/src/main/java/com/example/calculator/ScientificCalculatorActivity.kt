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
    lateinit var binding: ActivityScientificCalculatorBinding
    private val vm = CalculatorViewModel()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("previousNumber", vm.previousNumber)
        outState.putString("currentNumber", vm.currentNumber)
        outState.putString("currentOperation", vm.currentOperation)
        outState.putBoolean("isAfterClear", vm.isAfterClear)
        outState.putString("tvResults", vm.tvResults)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        vm.previousNumber = savedInstanceState.getString("previousNumber", "")
        vm.currentNumber = savedInstanceState.getString("currentNumber", "0")
        vm.currentOperation = savedInstanceState.getString("currentOperation", "")
        vm.isAfterClear = savedInstanceState.getBoolean("isAfterClear", false)
        vm.tvResults = savedInstanceState.getString("tvResults", "0")
        binding.tvResults.text = vm.tvResults
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

        fun updateNumber(number: String) {
            vm.updateNumber(number)
            binding.tvResults.text = vm.tvResults
        }

        fun updateOperation(operation: String) {
            vm.updateOperation(operation)
            binding.tvResults.text = vm.tvResults
        }

        // Scientific

        binding.sin.setOnClickListener {
            vm.sin()
            binding.tvResults.text = vm.tvResults
        }

        binding.cos.setOnClickListener {
            vm.cos()
            binding.tvResults.text = vm.tvResults
        }

        binding.tan.setOnClickListener {
            vm.tan()
            binding.tvResults.text = vm.tvResults
        }

        binding.log.setOnClickListener {
            vm.log()
            binding.tvResults.text = vm.tvResults
        }

        binding.ln.setOnClickListener {
            vm.ln()
            binding.tvResults.text = vm.tvResults
        }

        binding.sqrt.setOnClickListener {
            vm.sqrt()
            binding.tvResults.text = vm.tvResults
        }

        binding.powerDouble.setOnClickListener {
            vm.powerDouble()
            binding.tvResults.text = vm.tvResults
        }

        binding.powerY.setOnClickListener {
            vm.powerY()
            binding.tvResults.text = vm.tvResults
        }

        binding.percent.setOnClickListener {
            vm.percent()
            binding.tvResults.text = vm.tvResults
        }

        // Clearing

        binding.allClear.setOnClickListener {
            vm.allClear()
            binding.tvResults.text = vm.tvResults
        }

        binding.clear.setOnClickListener {
            vm.clear()
            binding.tvResults.text = vm.tvResults
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
        binding.equals.setOnClickListener {
            vm.equals()
            binding.tvResults.text = vm.tvResults
        }

        // Other

        binding.dot.setOnClickListener {
            vm.dot()
            binding.tvResults.text = vm.tvResults
        }

        binding.plusMinus.setOnClickListener {
            vm.plusMinus()
            binding.tvResults.text = vm.tvResults
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}