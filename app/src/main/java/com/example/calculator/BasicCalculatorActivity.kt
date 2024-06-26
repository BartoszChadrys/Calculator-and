package com.example.calculator

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityBasicCalculatorBinding
import java.lang.ref.WeakReference

class BasicCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasicCalculatorBinding
    private val vm: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBasicCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm.context = WeakReference(this)
        binding.tvResults.text = vm.tvResults
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